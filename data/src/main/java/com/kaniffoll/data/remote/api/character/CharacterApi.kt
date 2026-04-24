package com.kaniffoll.data.remote.api.character

import com.kaniffoll.data.remote.model.CharacterDto

interface CharacterApi {
    fun getCharacters(): List<CharacterDto>
}