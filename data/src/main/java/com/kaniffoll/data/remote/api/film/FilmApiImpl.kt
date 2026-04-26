package com.kaniffoll.data.remote.api.film

import com.kaniffoll.data.remote.model.FilmDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import jakarta.inject.Inject

class FilmApiImpl @Inject constructor(private val client: HttpClient): FilmApi {
    override suspend fun getByUrl(url: String): FilmDto {
        return client.get(url).body()
    }
}