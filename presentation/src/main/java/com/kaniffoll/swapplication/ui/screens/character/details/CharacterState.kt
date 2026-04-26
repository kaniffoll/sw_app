package com.kaniffoll.swapplication.ui.screens.character.details

import com.kaniffoll.swapplication.model.CharacterUI

sealed interface CharacterState {
    data object IDLE: CharacterState
    data class Success(val character: CharacterUI): CharacterState
    data class Error(val e: Exception): CharacterState
}