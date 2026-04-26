package com.kaniffoll.data.repo

import com.kaniffoll.data.remote.api.palnet.PlanetApi
import com.kaniffoll.data.utils.toPlanet
import com.kaniffoll.domain.model.Planet
import com.kaniffoll.domain.repo.PlanetRepository
import jakarta.inject.Inject

class PlanetRepositoryImpl @Inject constructor(private val planetApi: PlanetApi): PlanetRepository {
    override suspend fun getPlanetByUrl(url: String): Result<Planet> {
        try {
            val res = planetApi.getPlanet(url)
            return Result.success(res.toPlanet())
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}