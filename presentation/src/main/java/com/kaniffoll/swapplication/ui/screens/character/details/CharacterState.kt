package com.kaniffoll.swapplication.ui.screens.character.details

import com.kaniffoll.domain.model.Character

sealed interface CharacterState {
    data object IDLE: CharacterState
    data class Success(val character: Character): CharacterState
    data class Error(val e: Exception): CharacterState
}