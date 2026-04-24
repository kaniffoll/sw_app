package com.kaniffoll.domain.model

data class Character(
    val id: Int,
    val name: String,
    val height: String,
    val mass: String,
    val hairColor: String,
    val skinColor: String,
    val eyeColor: String,
    val birthYear: String,
    val gender: String,
    val homeworld: Planet? = null,
    val films: List<Film> = emptyList(),
    val species: List<Species> = emptyList(),
    val vehicles: List<Vehicle> = emptyList(),
    val starships: List<Starship> = emptyList(),
)
