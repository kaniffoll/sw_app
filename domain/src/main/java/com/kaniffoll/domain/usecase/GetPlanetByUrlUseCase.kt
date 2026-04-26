package com.kaniffoll.domain.usecase

import com.kaniffoll.domain.repo.PlanetRepository
import jakarta.inject.Inject

class GetPlanetByUrlUseCase @Inject constructor(private val planetRepository: PlanetRepository) {
    suspend operator fun invoke(url: String) = planetRepository.getPlanetByUrl(url)
}