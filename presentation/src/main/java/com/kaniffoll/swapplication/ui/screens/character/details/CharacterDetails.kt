package com.kaniffoll.swapplication.ui.screens.character.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.kaniffoll.domain.model.Planet
import com.kaniffoll.swapplication.R
import com.kaniffoll.swapplication.model.CharacterUI
import com.kaniffoll.swapplication.ui.components.CustomCircularProgressIndicator
import com.kaniffoll.swapplication.ui.components.ErrorIndicator
import com.kaniffoll.swapplication.ui.res.Dimens

@Composable
fun CharacterDetails(
    viewModel: CharacterDetailsViewModel,
    provideTitle: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val characterState by viewModel.characterState.collectAsState()

    when (val state = characterState) {
        is CharacterState.Error -> {
            ErrorIndicator(
                e = state.e,
                onRetry = viewModel::loadState
            )
        }

        CharacterState.IDLE -> {
            CustomCircularProgressIndicator()
        }

        is CharacterState.Success -> {
            provideTitle(state.character.name)
            MainContent(
                character = state.character,
                modifier = modifier
            )
        }
    }
}

@Composable
private fun MainContent(
    character: CharacterUI,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.padding(Dimens.medium),
        verticalArrangement = Arrangement.spacedBy(Dimens.medium)
    ) {
        item {
            ContentColumn {
                Text(stringResource(R.string.basic_info))

                val basicMap = mapOf(
                    R.string.birth_year to character.birthYear,
                    R.string.height to character.height,
                    R.string.mass to character.mass,
                    R.string.gender to character.gender
                )

                basicMap.keys.toList().forEach {
                    InfoCard(
                        title = stringResource(it),
                        subTitle = basicMap[it]!!
                    )
                }
            }
        }

        item {
            ContentColumn {
                Text(stringResource(R.string.species))
                if (character.species.isEmpty()) {
                    NoInfoCard()
                } else {
                    character.species.forEach {
                        InfoCard(title = it.name, subTitle = it.classification)
                    }
                }
            }
        }

        item {
            ContentColumn {
                Text(stringResource(R.string.films))
                if (character.films.isEmpty()) {
                    NoInfoCard()
                } else {
                    character.films.forEach {
                        InfoCard(title = it.title, subTitle = it.openingCrawl)
                    }
                }
            }
        }

        item {
            ContentColumn {
                Text(stringResource(R.string.additional_information))
                InfoCard(
                    title = stringResource(R.string.hair_color),
                    subTitle = character.hairColor
                )
                InfoCard(
                    title = stringResource(R.string.eye_color),
                    subTitle = character.eyeColor
                )
            }
        }

        item {
            ContentColumn {
                Text(stringResource(R.string.homeworld))
                if (character.homeworld != null) PlanetCard(character.homeworld) else NoInfoCard()
            }
        }
    }
}

@Composable
private fun PlanetCard(
    planet: Planet,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = ShapeDefaults.Small,
    ) {
        Text(
            modifier = Modifier.padding(Dimens.medium),
            text = planet.name
        )
    }
}

@Composable
private fun ContentColumn(
    modifier: Modifier = Modifier,
    content: @Composable (ColumnScope.() -> Unit)
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(Dimens.small2),
        content = content
    )
}

@Composable
private fun NoInfoCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = ShapeDefaults.Small,
    ) {
        Text(
            modifier = Modifier.padding(Dimens.medium),
            text = stringResource(R.string.no_info)
        )
    }
}

@Composable
private fun InfoCard(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = ShapeDefaults.Small,
    ) {
        Column(modifier = Modifier.padding(Dimens.medium)) {
            Text(
                text = title
            )
            Text(
                text = subTitle,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}