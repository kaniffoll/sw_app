package com.kaniffoll.swapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.kaniffoll.di.AppComponentProvider
import com.kaniffoll.swapplication.di.DaggerMainActivityComponent
import com.kaniffoll.swapplication.di.viewmodel.ViewModelFactory
import com.kaniffoll.swapplication.navigation.NavRoot
import com.kaniffoll.swapplication.ui.theme.SWApplicationTheme
import jakarta.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var factory: ViewModelFactory

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
                NavRoot(factory)
            }
        }
    }
}