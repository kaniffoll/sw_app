package com.kaniffoll.data.remote.api.species

import com.kaniffoll.data.remote.model.SpeciesDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import jakarta.inject.Inject

class SpeciesApiImpl @Inject constructor(private val client: HttpClient): SpeciesApi {
    override suspend fun getByUrl(url: String): SpeciesDto {
        return client.get(url).body()
    }
}