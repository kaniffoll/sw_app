package com.kaniffoll.swapplication.ui.screens.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaniffoll.domain.model.Character
import com.kaniffoll.domain.usecase.GetCharactersUseCase
import io.github.ahmad_hamwi.compose.pagination.PaginationState
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharactersListViewModel @Inject constructor(private val getCharactersUseCase: GetCharactersUseCase) :
    ViewModel() {
    val paginationState = PaginationState<String?, Character>(
        initialPageKey = null,
        onRequestPage = { loadPage(it) }
    )

    fun loadPage(nextUrl: String?) {
        viewModelScope.launch {
            try {
                val result = getCharactersUseCase(nextUrl)


                result.fold(
                    onSuccess = {
                        val page = result.getOrNull()!!
                        paginationState.appendPage(
                            items = page.items,
                            nextPageKey = page.nextKey,
                            isLastPage = page.nextKey == null
                        )
                    },
                    onFailure = {
                        paginationState.setError(it as Exception)
                    }
                )
            } catch (e: Exception) {
                paginationState.setError(e)
            }
        }
    }
}