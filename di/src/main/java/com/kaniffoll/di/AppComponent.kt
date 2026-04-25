package com.kaniffoll.di

import android.content.Context
import com.kaniffoll.domain.usecase.GetCharactersUseCase
import dagger.BindsInstance
import dagger.Component
import jakarta.inject.Singleton

@Singleton
@Component(modules = [RepoModule::class, ApiModule::class, RemoteModule::class])
interface AppComponent {

    fun getCharactersUseCase(): GetCharactersUseCase

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }
}