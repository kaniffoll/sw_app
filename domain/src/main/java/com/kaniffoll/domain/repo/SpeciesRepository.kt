package com.kaniffoll.domain.repo

import com.kaniffoll.domain.model.Species

interface SpeciesRepository {
    suspend fun getSpeciesByUrl(url: String): Result<Species>
}