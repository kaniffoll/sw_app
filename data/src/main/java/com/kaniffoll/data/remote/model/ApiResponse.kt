package com.kaniffoll.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    val next: String?,
    val results: List<T>
)
