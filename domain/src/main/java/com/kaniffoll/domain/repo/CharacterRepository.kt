package com.kaniffoll.domain.repo

import com.kaniffoll.domain.model.Character

interface CharacterRepository {
    fun getCharacters(): List<Character>
}