package com.kaniffoll.domain.usecase

import com.kaniffoll.domain.repo.CharacterRepository
import jakarta.inject.Inject

class SearchByNameUseCase @Inject constructor(private val repository: CharacterRepository) {
    suspend operator fun invoke(name: String, next: String?) = repository.searchByName(name, next)
}