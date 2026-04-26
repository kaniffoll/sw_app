package com.kaniffoll.swapplication.ui.screens.character.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class CharacterDetailsViewModelFactory(
    private val assistedFactory: CharacterDetailsViewModel.Factory,
    private val id: Int,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return assistedFactory.create(id) as T
    }
}