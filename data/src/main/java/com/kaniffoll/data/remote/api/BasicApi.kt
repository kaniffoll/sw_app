package com.kaniffoll.data.remote.api

interface BasicApi <out T> {
    suspend fun getByUrl(url: String): T
}