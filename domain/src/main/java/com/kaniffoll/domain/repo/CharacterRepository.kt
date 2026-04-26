package com.kaniffoll.domain.repo

import com.kaniffoll.domain.model.Character
import com.kaniffoll.domain.model.Page

interface CharacterRepository {
    suspend fun getCharacters(url: String?): Result<Page<Character>>
    suspend fun getCharacterById(id: Int): Result<Character>
    suspend fun searchByName(name: String, next: String?): Result<Page<Character>>
}