package com.kaniffoll.domain.usecase

import com.kaniffoll.domain.repo.CharacterRepository

class GetCharactersUseCase(private val repository: CharacterRepository) {
    suspend operator fun invoke(url: String?) = repository.getCharacters(url)
}