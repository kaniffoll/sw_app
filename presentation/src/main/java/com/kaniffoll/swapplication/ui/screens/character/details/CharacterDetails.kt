package com.kaniffoll.swapplication.ui.screens.character.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.kaniffoll.domain.model.Character
import com.kaniffoll.swapplication.R
import com.kaniffoll.swapplication.ui.components.CustomCircularProgressIndicator
import com.kaniffoll.swapplication.ui.components.ErrorIndicator
import com.kaniffoll.swapplication.ui.res.Dimens

@Composable
fun CharacterDetails(
    viewModel: CharacterDetailsViewModel,
    provideTitle: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val characterState by viewModel.character.collectAsState()

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
    character: Character,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier.padding(Dimens.medium)) {
        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(Dimens.small2)
            ) {
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
                text = subTitle
            )
        }
    }
}