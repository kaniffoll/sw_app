package com.kaniffoll.swapplication.model

import com.kaniffoll.domain.model.Film
import com.kaniffoll.domain.model.Planet
import com.kaniffoll.domain.model.Species
import com.kaniffoll.domain.model.Starship
import com.kaniffoll.domain.model.Vehicle
import com.kaniffoll.domain.model.Character

data class CharacterUI(
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

fun Character.toUI(): CharacterUI {
    return CharacterUI(
        id,
        name,
        height,
        mass,
        hairColor,
        skinColor,
        eyeColor,
        birthYear,
        gender
    )
}
