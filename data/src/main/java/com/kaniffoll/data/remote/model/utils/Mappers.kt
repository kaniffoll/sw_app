package com.kaniffoll.data.remote.model.utils

import com.kaniffoll.data.remote.model.CharacterDto
import com.kaniffoll.domain.model.Character

fun CharacterDto.toCharacter(): Character {
    return Character(
        id = extractIdFromUrl(this.url),
        name = name,
        height = height,
        mass = mass,
        hairColor = hairColor,
        skinColor = skinColor,
        eyeColor = eyeColor,
        birthYear = birthYear,
        gender = gender
    )
}