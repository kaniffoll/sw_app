package com.kaniffoll.domain.repo

import com.kaniffoll.domain.model.Film

interface FilmRepository {
    suspend fun getFilmByUrl(url: String): Result<Film>
}