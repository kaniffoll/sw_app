package com.kaniffoll.domain.usecase

import com.kaniffoll.domain.model.Species
import com.kaniffoll.domain.repo.SpeciesRepository
import jakarta.inject.Inject

class GetSpeciesByIdUseCase @Inject constructor(private val repository: SpeciesRepository) {
    suspend operator fun invoke(urls: List<String>): List<Result<Species>> {
        return urls.map { url -> repository.getSpeciesByUrl(url) }
    }
}