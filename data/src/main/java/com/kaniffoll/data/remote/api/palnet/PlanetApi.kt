package com.kaniffoll.data.remote.api.palnet

import com.kaniffoll.data.remote.model.PlanetDto

interface PlanetApi {
    suspend fun getPlanet(url: String): PlanetDto
}