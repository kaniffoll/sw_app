package com.kaniffoll.data.utils

import com.kaniffoll.data.local.model.CharacterEntity
import com.kaniffoll.data.remote.model.CharacterDto
import com.kaniffoll.data.remote.model.FilmDto
import com.kaniffoll.data.remote.model.PlanetDto
import com.kaniffoll.domain.model.Character
import com.kaniffoll.domain.model.Film
import com.kaniffoll.domain.model.Planet

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
        gender = gender,
        homeworld = homeworld,
        films = films,
        species = species,
        vehicles = vehicles,
        starships = starships
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

fun PlanetDto.toPlanet(): Planet {
    return Planet(
        id = extractIdFromUrl(this.url),
        name = name,
        climate = climate,
        terrain = terrain,
        population = population,
        diameter = diameter,
        gravity = gravity,
        orbitalPeriod = orbitalPeriod,
        surfaceWater = surfaceWater,
        rotationPeriod = rotationPeriod,
    )
}

fun FilmDto.toFilm(): Film {
    return Film(
        id = extractIdFromUrl(this.url),
        title = title,
        episodeId = episodeId,
        openingCrawl = openingCrawl,
        director = director,
        producer = producer,
        releaseDate = releaseDate,
    )
}