package com.kaniffoll.swapplication.ui.screens.character.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaniffoll.domain.usecase.GetCharacterByIdUseCase
import com.kaniffoll.swapplication.model.toUI
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharacterDetailsViewModel @AssistedInject constructor(
    @Assisted val id: Int,
    private val useCase: GetCharacterByIdUseCase
) : ViewModel() {

    private var _character = MutableStateFlow<CharacterState>(CharacterState.IDLE)
    val character = _character.asStateFlow()

    init {
        Log.d("VM", "INIT")
        loadState()
    }

    fun loadState() {
        viewModelScope.launch {
            val result = useCase(id)
            result.fold(
                onSuccess = {
                    _character.value = CharacterState.Success(it.toUI())
                },
                onFailure = {
                    _character.value = CharacterState.Error(it as Exception)
                }
            )
        }
    }

    override fun onCleared() {
        Log.d("VM", "CLEARED")
        super.onCleared()
    }

    @AssistedFactory
    interface Factory {
        fun create(id: Int): CharacterDetailsViewModel
    }
}