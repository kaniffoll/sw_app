package com.kaniffoll.domain.model

data class Film(
    val id: Int,
    val title: String,
    val episodeId: Int,
    val openingCrawl: String,
    val director: String,
    val producer: String,
    val releaseDate: String,
    val characters: List<Character> = emptyList(),
    val planets: List<Planet> = emptyList(),
    val starships: List<Starship> = emptyList(),
    val vehicles: List<Vehicle> = emptyList(),
    val species: List<Species> = emptyList(),
)
