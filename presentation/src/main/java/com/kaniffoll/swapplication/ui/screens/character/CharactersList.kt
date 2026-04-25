package com.kaniffoll.swapplication.ui.screens.character

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kaniffoll.domain.model.Character
import com.kaniffoll.swapplication.ui.components.CustomCircularProgressIndicator
import com.kaniffoll.swapplication.ui.components.ErrorIndicator
import com.kaniffoll.swapplication.ui.res.Dimens
import io.github.ahmad_hamwi.compose.pagination.PaginatedLazyColumn

@Composable
fun CharactersList(
    viewModel: CharactersListViewModel,
    modifier: Modifier = Modifier
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
            CharacterCard(item)
        }
    }
}

@Composable
private fun CharacterCard(
    character: Character,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        modifier = modifier.fillMaxWidth(),
        shape = ShapeDefaults.Small
    ) {
        Text(
            modifier = Modifier.padding(Dimens.medium),
            text = character.name
        )
    }
}