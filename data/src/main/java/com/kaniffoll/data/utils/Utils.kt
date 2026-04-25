package com.kaniffoll.data.utils

fun extractIdFromUrl(url: String): Int {
    return url.trimEnd('/')
        .substringAfterLast('/')
        .toInt()
}
