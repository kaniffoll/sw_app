package com.kaniffoll.data.remote.api.palnet

import com.kaniffoll.data.remote.model.PlanetDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import jakarta.inject.Inject

class PlanetApiImpl @Inject constructor(private val client: HttpClient): PlanetApi {
    override suspend fun getPlanet(url: String): PlanetDto {
        return client.get(url).body()
    }
}