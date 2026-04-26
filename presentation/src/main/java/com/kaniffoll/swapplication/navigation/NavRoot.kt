package com.kaniffoll.swapplication.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.kaniffoll.swapplication.R
import com.kaniffoll.swapplication.di.viewmodel.ViewModelFactory
import com.kaniffoll.swapplication.navigation.model.Route
import com.kaniffoll.swapplication.ui.screens.character.details.CharacterDetails
import com.kaniffoll.swapplication.ui.screens.character.details.CharacterDetailsViewModel
import com.kaniffoll.swapplication.ui.screens.character.details.CharacterDetailsViewModelFactory
import com.kaniffoll.swapplication.ui.screens.character.CharactersList
import com.kaniffoll.swapplication.ui.screens.character.CharactersListViewModel
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavRoot(
    mainFactory: ViewModelFactory,
    charFactory: CharacterDetailsViewModel.Factory,
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

    var providedTitle by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            SWTopAppBar(
                entry = rootBackStack.last() as Route,
                providedTitle = providedTitle,
                onIconClick = { rootBackStack.removeAt(rootBackStack.lastIndex) }
            )
        }
    ) { innerPadding ->

        NavDisplay(
            modifier = modifier.padding(innerPadding),
            backStack = rootBackStack,
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ),
            entryProvider = entryProvider {
                entry<Route.CharsList> {
                    val viewModel: CharactersListViewModel = viewModel(factory = mainFactory)
                    CharactersList(viewModel) {
                        rootBackStack.add(Route.CharsDetails(it))
                    }
                }
                entry<Route.CharsDetails> {
                    val viewModel: CharacterDetailsViewModel = viewModel(
                        factory = CharacterDetailsViewModelFactory(
                            assistedFactory = charFactory,
                            id = it.id
                        )
                    )
                    CharacterDetails(
                        viewModel = viewModel,
                        provideTitle = { title -> providedTitle = title }
                    )
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SWTopAppBar(
    modifier: Modifier = Modifier,
    entry: Route,
    providedTitle: String,
    onIconClick: () -> Unit = {}
) {
    val isList = entry is Route.CharsList

    val title = when (entry) {
        is Route.CharsList -> stringResource(R.string.characters)
        is Route.CharsDetails -> providedTitle
    }

    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = title,
                textAlign = TextAlign.Center
            )
        },
        navigationIcon = {
            if (!isList) {
                Icon(
                    painter = painterResource(R.drawable.baseline_arrow_back_24),
                    contentDescription = stringResource(R.string.arrow_back),
                    modifier = Modifier.clickable(onClick = onIconClick)
                )
            }
        }
    )
}
