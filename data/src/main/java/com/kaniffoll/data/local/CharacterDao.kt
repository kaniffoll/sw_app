package com.kaniffoll.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.kaniffoll.data.local.model.CharacterEntity

@Dao
interface CharacterDao {
    @Query("SELECT * FROM characters")
    suspend fun getCharacters(): List<CharacterEntity>

    @Query("SELECT * FROM characters WHERE id = :id")
    suspend fun getCharacterById(id: Int): CharacterEntity

    @Upsert
    suspend fun saveCharacters(character: List<CharacterEntity>)
}