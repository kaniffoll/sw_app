package com.kaniffoll.domain.usecase

import com.kaniffoll.domain.repo.CharacterRepository
import jakarta.inject.Inject

class GetCharactersUseCase @Inject constructor(private val repository: CharacterRepository) {
    suspend operator fun invoke(url: String?) = repository.getCharacters(url)
}