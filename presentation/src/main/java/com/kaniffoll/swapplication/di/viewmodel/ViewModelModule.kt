package com.kaniffoll.swapplication.di.viewmodel

import androidx.lifecycle.ViewModel
import com.kaniffoll.swapplication.ui.screens.character.CharactersListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CharactersListViewModel::class)
    fun bindCharsListViewModel(
        vm: CharactersListViewModel
    ): ViewModel
}