package com.kaniffoll.data.remote.api.film

import com.kaniffoll.data.remote.model.FilmDto

interface FilmApi {
    suspend fun getFilm(url: String): FilmDto
}