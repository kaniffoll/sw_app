package com.kaniffoll.data.repo

import com.kaniffoll.data.remote.api.species.SpeciesApi
import com.kaniffoll.data.utils.toSpecies
import com.kaniffoll.domain.model.Species
import com.kaniffoll.domain.repo.SpeciesRepository
import jakarta.inject.Inject

class SpeciesRepositoryImpl @Inject constructor(private val api: SpeciesApi) : SpeciesRepository {
    override suspend fun getSpeciesByUrl(url: String): Result<Species> {
        return try {
            Result.success(api.getByUrl(url).toSpecies())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}