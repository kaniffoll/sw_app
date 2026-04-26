package com.kaniffoll.swapplication.navigation.model

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route: NavKey {

    @Serializable
    data object CharsList: Route, NavKey

    @Serializable
    data object CharsDetails: Route, NavKey
}