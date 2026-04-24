package com.kaniffoll.data.remote.model.utils

fun extractIdFromUrl(url: String): Int {
    return url.trimEnd('/')
        .substringAfterLast('/')
        .toInt()
}
