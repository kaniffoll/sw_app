package com.kaniffoll.swapplication.ui.screens.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaniffoll.domain.usecase.GetCharactersUseCase
import com.kaniffoll.domain.usecase.SearchByNameUseCase
import com.kaniffoll.swapplication.model.CharacterUI
import com.kaniffoll.swapplication.model.toUI
import io.github.ahmad_hamwi.compose.pagination.PaginationState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharactersListViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val searchByNameUseCase: SearchByNameUseCase
) : ViewModel() {

    private var sourceState = MutableStateFlow<PaginationSource>(PaginationSource.Default)

    val paginationState = PaginationState<String?, CharacterUI>(
        initialPageKey = null, onRequestPage = { loadPage(it) })

    fun loadPage(nextUrl: String?) {
        viewModelScope.launch {
            try {
                val result = when (val state = sourceState.value) {
                    PaginationSource.Default -> getCharactersUseCase(nextUrl)
                    is PaginationSource.Search -> searchByNameUseCase(
                        name = state.query,
                        next = nextUrl
                    )
                }

                result.fold(onSuccess = {
                    val page = result.getOrNull()!!
                    paginationState.appendPage(
                        items = page.items.map { it.toUI() },
                        nextPageKey = page.nextKey,
                        isLastPage = page.nextKey == null
                    )
                }, onFailure = {
                    paginationState.setError(it as Exception)
                })
            } catch (e: Exception) {
                paginationState.setError(e)
            }
        }
    }

    fun search(name: String) {
        paginationState.refresh()
        if (name.isNotBlank()) {
            sourceState.value = PaginationSource.Search(name)
        } else {
            sourceState.value = PaginationSource.Default
        }
    }
}

sealed interface PaginationSource {
    data object Default : PaginationSource
    data class Search(val query: String) : PaginationSource
}