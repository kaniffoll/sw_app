package com.kaniffoll.di

import com.kaniffoll.data.remote.api.character.CharacterApi
import com.kaniffoll.data.remote.api.character.CharacterApiImpl
import com.kaniffoll.data.remote.http.HttpClientProvider
import com.kaniffoll.data.repo.CharacterRepositoryImpl
import com.kaniffoll.domain.repo.CharacterRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.ktor.client.HttpClient
import jakarta.inject.Singleton

@Module
interface RepoModule {
    @Binds
    fun bindsCharacterRepository(impl: CharacterRepositoryImpl): CharacterRepository
}

@Module
interface ApiModule {
    @Binds
    fun bindsCharacterApi(impl: CharacterApiImpl): CharacterApi
}

@Module
object RemoteModule {
    @Provides
    fun provideHttpClientProvider() = HttpClientProvider()

    @Provides
    @Singleton
    fun providesHttpClient(httpClientProvider: HttpClientProvider): HttpClient = httpClientProvider()
}