package com.kaniffoll.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpeciesDto(
    val name: String,
    val classification: String,
    val designation: String,
    @SerialName("average_height")
    val averageHeight: String,
    @SerialName("average_lifespan")
    val averageLifespan: String,
    @SerialName("eye_colors")
    val eyeColors: String,
    @SerialName("hair_colors")
    val hairColors: String,
    @SerialName("skin_colors")
    val skinColors: String,
    val language: String,
    val homeworld: String,
    val people: List<String> = emptyList(),
    val films: List<String> = emptyList(),
    val url: String
)
