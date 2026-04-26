package com.kaniffoll.swapplication.ui.screens.character

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.kaniffoll.swapplication.R
import com.kaniffoll.swapplication.model.CharacterUI
import com.kaniffoll.swapplication.ui.components.CustomCircularProgressIndicator
import com.kaniffoll.swapplication.ui.components.ErrorIndicator
import com.kaniffoll.swapplication.ui.res.Dimens
import io.github.ahmad_hamwi.compose.pagination.PaginatedLazyColumn

@Composable
fun CharactersList(
    viewModel: CharactersListViewModel,
    modifier: Modifier = Modifier,
    onCardClick: (id: Int) -> Unit
) {
    val paginationState = viewModel.paginationState

    PaginatedLazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.medium),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Dimens.small2),
        paginationState = paginationState,
        firstPageProgressIndicator = { CustomCircularProgressIndicator() },
        firstPageErrorIndicator = { e ->
            ErrorIndicator(e) { paginationState.retryLastFailedRequest() }
        },
        newPageErrorIndicator = { e ->
            ErrorIndicator(e) { paginationState.retryLastFailedRequest() }
        }
    ) {
        items(
            paginationState.allItems!!,
        ) { item ->
            CharacterCard(character = item, onClick = onCardClick)
        }
    }
}

@Composable
private fun CharacterCard(
    character: CharacterUI,
    modifier: Modifier = Modifier,
    onClick: (id: Int) -> Unit
) {
    OutlinedCard(
        modifier = modifier.fillMaxWidth(),
        shape = ShapeDefaults.Small,
        onClick = { onClick(character.id) }
    ) {
        val paramsMap = mapOf(
            R.string.height to character.height,
            R.string.mass to character.mass,
            R.string.gender to character.gender,
            R.string.skin_color to character.skinColor
        )
        Column(modifier = Modifier.padding(Dimens.medium)) {
            Text(
                text = character.name
            )
            LazyRow(horizontalArrangement = Arrangement.spacedBy(Dimens.small2)) {
                items(paramsMap.keys.toList()) { key ->
                    Text(
                        text = "${stringResource(key)}: ${paramsMap[key]}",
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
    }
}