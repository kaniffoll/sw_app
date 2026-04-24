package com.kaniffoll.domain.model

data class Species(
    val name: String,
    val classification: String,
    val designation: String,
    val averageHeight: String,
    val averageLifespan: String,
    val eyeColors: String,
    val hairColors: String,
    val skinColors: String,
    val language: String,
    val homeworld: Planet? = null,
    val people: List<Character> = emptyList(),
    val films: List<Film> = emptyList(),
)
