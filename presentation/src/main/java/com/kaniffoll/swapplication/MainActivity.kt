package com.kaniffoll.swapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.kaniffoll.di.AppComponentProvider
import com.kaniffoll.swapplication.di.DaggerMainActivityComponent
import com.kaniffoll.swapplication.ui.screens.character.CharactersList
import com.kaniffoll.swapplication.ui.screens.character.CharactersListViewModel
import com.kaniffoll.swapplication.ui.theme.SWApplicationTheme
import jakarta.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject
    lateinit var charactersListViewModel: CharactersListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appComponent = (application as AppComponentProvider).appComponent
        DaggerMainActivityComponent.builder()
            .appComponent(appComponent)
            .build()
            .inject(this)
        enableEdgeToEdge()
        setContent {
            SWApplicationTheme {
                Scaffold {
                    CharactersList(
                        modifier = Modifier.padding(it),
                        viewModel = charactersListViewModel
                    )
                }
            }
        }
    }
}