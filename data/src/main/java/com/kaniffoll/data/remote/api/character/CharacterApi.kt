package com.kaniffoll.data.remote.api.character

import com.kaniffoll.data.remote.model.ApiResponse
import com.kaniffoll.data.remote.model.CharacterDto

interface CharacterApi {
    suspend fun getCharacters(url: String): ApiResponse<CharacterDto>
    suspend fun getCharacterById(id: Int): CharacterDto
    suspend fun searchByName(name: String): ApiResponse<CharacterDto>
}