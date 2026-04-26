package com.kaniffoll.di

import android.content.Context
import com.kaniffoll.domain.usecase.GetCharacterByIdUseCase
import com.kaniffoll.domain.usecase.GetCharactersUseCase
import com.kaniffoll.domain.usecase.GetFilmsByUrlsUseCase
import com.kaniffoll.domain.usecase.GetPlanetByUrlUseCase
import com.kaniffoll.domain.usecase.GetSpeciesByIdUseCase
import com.kaniffoll.domain.usecase.SearchByNameUseCase
import dagger.BindsInstance
import dagger.Component
import jakarta.inject.Singleton

@Singleton
@Component(modules = [RepoModule::class, ApiModule::class, RemoteModule::class, DBModule::class])
interface AppComponent {

    fun getCharactersUseCase(): GetCharactersUseCase
    fun getCharacterByIdUseCase(): GetCharacterByIdUseCase
    fun getPlanetByUrlUseCase(): GetPlanetByUrlUseCase

    fun getFilmsByUrlUseCase(): GetFilmsByUrlsUseCase

    fun getSpeciesByUrlUseCase(): GetSpeciesByIdUseCase

    fun searchByNameUseCase(): SearchByNameUseCase

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }
}