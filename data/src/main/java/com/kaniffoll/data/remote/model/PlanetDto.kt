package com.kaniffoll.data.remote.model

import kotlinx.serialization.SerialName
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
    @SerialName("orbital_period")
    val orbitalPeriod: String,
    @SerialName("rotation_period")
    val rotationPeriod: String,
    @SerialName("surface_water")
    val surfaceWater: String,
    val residents: List<String>,
    val films: List<String>,
    val url: String
)
