package com.kaniffoll.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kaniffoll.data.local.model.CharacterEntity

@Database(
    entities = [CharacterEntity::class],
    version = RoomRes.DATABASE_VERSION,
)
abstract class SWDatabase: RoomDatabase() {
    abstract val charDao: CharacterDao
}