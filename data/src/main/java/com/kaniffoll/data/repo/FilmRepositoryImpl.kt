package com.kaniffoll.data.repo

import com.kaniffoll.data.remote.api.film.FilmApi
import com.kaniffoll.data.utils.toFilm
import com.kaniffoll.domain.model.Film
import com.kaniffoll.domain.repo.FilmRepository
import jakarta.inject.Inject

class FilmRepositoryImpl @Inject constructor(private val api: FilmApi): FilmRepository {
    override suspend fun getFilmByUrl(url: String): Result<Film> {
        return try {
            Result.success(api.getFilm(url).toFilm())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}