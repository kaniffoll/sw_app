package com.kaniffoll.domain.model

data class Planet(
    val id: Int,
    val name: String,
    val climate: String,
    val terrain: String,
    val population: String,
    val diameter: String,
    val gravity: String,
    val orbitalPeriod: String,
    val rotationPeriod: String,
    val surfaceWater: String,
    val residents: List<Character> = emptyList(),
    val films: List<Film> = emptyList()
)
