package com.kaniffoll.domain.usecase

import com.kaniffoll.domain.repo.CharacterRepository

class GetCharactersUseCase(private val repository: CharacterRepository) {
    operator fun invoke() = repository.getCharacters()
}