package com.kaniffoll.data.session

sealed interface CacheState {
    data object IDLE: CacheState
    data object END: CacheState
    data class NEXT(val url: String): CacheState
}