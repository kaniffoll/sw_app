package com.kaniffoll.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class PlanetDto(
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
    val residents: List<String>,
    val films: List<String>
)
