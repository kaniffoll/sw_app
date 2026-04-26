package com.kaniffoll.swapplication.ui.screens.character.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaniffoll.domain.model.Character
import com.kaniffoll.domain.usecase.GetCharacterByIdUseCase
import com.kaniffoll.domain.usecase.GetFilmsByUrlsUseCase
import com.kaniffoll.domain.usecase.GetPlanetByUrlUseCase
import com.kaniffoll.swapplication.model.toUI
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharacterDetailsViewModel @AssistedInject constructor(
    @Assisted val id: Int,
    private val getCharUseCase: GetCharacterByIdUseCase,
    private val getPlanetUseCase: GetPlanetByUrlUseCase,
    private val getFilmsUseCase: GetFilmsByUrlsUseCase
) : ViewModel() {

    private var _character = MutableStateFlow<CharacterState>(CharacterState.IDLE)
    val character = _character.asStateFlow()

    init {
        loadState()
    }

    fun loadState() {
        viewModelScope.launch {
            val result = getCharUseCase(id)
            result.fold(onSuccess = {
                _character.value = CharacterState.Success(it.toUI())
                loadAdditionalInfo(it)
            }, onFailure = {
                _character.value = CharacterState.Error(it as Exception)
            })
        }
    }

    suspend fun loadAdditionalInfo(character: Character) {
        val planetRes = getPlanetUseCase(character.homeworld!!)

        check(_character.value is CharacterState.Success)

        if (planetRes.isSuccess) {
            val tmp = (_character.value as CharacterState.Success).character
            _character.value =
                CharacterState.Success(
                    tmp.copy(homeworld = planetRes.getOrNull()!!)
                )
        }

        val filmResults = getFilmsUseCase(character.films)
        if (filmResults.all { it.isSuccess }) {
            val tmp = (_character.value as CharacterState.Success).character
            _character.value =
                CharacterState.Success(
                    tmp.copy(films = filmResults.map { it.getOrNull()!! })
                )
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(id: Int): CharacterDetailsViewModel
    }
}