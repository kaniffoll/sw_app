package com.kaniffoll.swapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.kaniffoll.di.AppComponentProvider
import com.kaniffoll.swapplication.di.DaggerMainActivityComponent
import com.kaniffoll.swapplication.di.viewmodel.ViewModelFactory
import com.kaniffoll.swapplication.navigation.NavRoot
import com.kaniffoll.swapplication.ui.screens.character.details.CharacterDetailsViewModel
import com.kaniffoll.swapplication.ui.theme.SWApplicationTheme
import jakarta.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var factory: ViewModelFactory

    //Очевидно не самое лучшее решение,
    //но с dagger2 достаточно сложно работать в связке c compose.
    //В теории можно создать универсальную фабрику
    @Inject
    lateinit var charVMFactory: CharacterDetailsViewModel.Factory

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
                NavRoot(
                    mainFactory = factory,
                    charFactory = charVMFactory
                )
            }
        }
    }
}