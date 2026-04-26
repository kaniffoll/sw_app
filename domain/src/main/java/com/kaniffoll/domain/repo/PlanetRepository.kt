package com.kaniffoll.domain.repo

import com.kaniffoll.domain.model.Planet

interface PlanetRepository {
    suspend fun getPlanetByUrl(url: String): Result<Planet>
}