package com.kaniffoll.domain.usecase

import com.kaniffoll.domain.repo.CharacterRepository
import jakarta.inject.Inject

class GetCharacterByIdUseCase @Inject constructor (private val characterRepository: CharacterRepository) {
    suspend operator fun invoke(id: Int) = characterRepository.getCharacterById(id)
}