package com.kaniffoll.domain.model

data class Page<T>(
    val items: List<T>,
    val nextKey: String?
)
