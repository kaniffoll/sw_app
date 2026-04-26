package com.kaniffoll.domain.usecase

import com.kaniffoll.domain.model.Film
import com.kaniffoll.domain.repo.FilmRepository
import jakarta.inject.Inject

class GetFilmsByUrlsUseCase @Inject constructor(private val repository: FilmRepository) {
    suspend operator fun invoke(urls: List<String>): List<Result<Film>> {
        return urls.map { url -> repository.getFilmByUrl(url) }
    }
}