package com.kaniffoll.swapplication.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.kaniffoll.swapplication.di.viewmodel.ViewModelFactory
import com.kaniffoll.swapplication.navigation.model.Route
import com.kaniffoll.swapplication.ui.screens.character.CharacterDetails
import com.kaniffoll.swapplication.ui.screens.character.CharactersList
import com.kaniffoll.swapplication.ui.screens.character.CharactersListViewModel
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@Composable
fun NavRoot(
    factory: ViewModelFactory,
    modifier: Modifier = Modifier
) {
    val rootBackStack = rememberNavBackStack(
        configuration = SavedStateConfiguration {
            serializersModule = SerializersModule {
                polymorphic(NavKey::class) {
                    subclass(Route.CharsList::class, Route.CharsList.serializer())
                    subclass(Route.CharsDetails::class, Route.CharsDetails.serializer())
                }
            }
        },
        Route.CharsList
    )

    Scaffold { innerPadding ->

        NavDisplay(
            modifier = modifier.padding(innerPadding),
            backStack = rootBackStack,
            entryProvider = entryProvider {
                entry<Route.CharsList> {
                    val viewModel: CharactersListViewModel = viewModel(factory = factory)
                    CharactersList(viewModel)
                }
                entry<Route.CharsDetails> {
                    CharacterDetails()
                }
            }
        )
    }
}
