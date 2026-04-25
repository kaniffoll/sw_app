package com.kaniffoll.data.utils

import com.kaniffoll.data.local.model.CharacterEntity
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

fun CharacterDto.toEntity(): CharacterEntity {
    return CharacterEntity(
        id = extractIdFromUrl(this.url),
        name = name,
        height = height,
        mass = mass,
        hairColor = hairColor,
        skinColor = skinColor,
        eyeColor = eyeColor,
        birthYear = birthYear,
        gender = gender,
    )
}

fun CharacterEntity.toCharacter(): Character {
    return Character(
        id = id,
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