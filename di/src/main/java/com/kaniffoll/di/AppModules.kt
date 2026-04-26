package com.kaniffoll.di

import android.content.Context
import androidx.room.Room
import com.kaniffoll.data.local.RoomRes
import com.kaniffoll.data.local.SWDatabase
import com.kaniffoll.data.remote.api.character.CharacterApi
import com.kaniffoll.data.remote.api.character.CharacterApiImpl
import com.kaniffoll.data.remote.api.palnet.PlanetApi
import com.kaniffoll.data.remote.api.palnet.PlanetApiImpl
import com.kaniffoll.data.remote.http.HttpClientProvider
import com.kaniffoll.data.repo.CharacterRepositoryImpl
import com.kaniffoll.data.repo.PlanetRepositoryImpl
import com.kaniffoll.domain.repo.CharacterRepository
import com.kaniffoll.domain.repo.PlanetRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.ktor.client.HttpClient
import jakarta.inject.Singleton

@Module
interface RepoModule {
    @Binds
    fun bindsCharacterRepository(impl: CharacterRepositoryImpl): CharacterRepository

    @Binds
    fun bindsPlanetRepository(impl: PlanetRepositoryImpl): PlanetRepository
}

@Module
interface ApiModule {
    @Binds
    fun bindsCharacterApi(impl: CharacterApiImpl): CharacterApi

    @Binds
    fun bindsPlanetApi(impl: PlanetApiImpl): PlanetApi
}

@Module
object RemoteModule {
    @Provides
    fun providesHttpClientProvider() = HttpClientProvider()

    @Provides
    @Singleton
    fun providesHttpClient(httpClientProvider: HttpClientProvider): HttpClient =
        httpClientProvider()
}

@Module
object DBModule {
    @Provides
    @Singleton
    fun providesDatabase(context: Context): SWDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = SWDatabase::class.java,
            name = RoomRes.DATABASE_NAME
        ).fallbackToDestructiveMigration(true).build()
    }

    @Provides
    @Singleton
    fun providesCharDao(database: SWDatabase) = database.charDao
}