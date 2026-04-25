package com.kaniffoll.swapplication.ui.screens.character

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.kaniffoll.domain.model.Character
import com.kaniffoll.swapplication.R
import com.kaniffoll.swapplication.ui.res.Dimens
import io.github.ahmad_hamwi.compose.pagination.PaginatedLazyColumn

@Composable
fun CharactersList(
    viewModel: CharactersListViewModel,
    modifier: Modifier = Modifier
) {
    val paginationState = viewModel.paginationState

    PaginatedLazyColumn(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Dimens.small),
        paginationState = paginationState,
        firstPageProgressIndicator = { CircularProgressIndicator() },
        newPageEmptyIndicator = { CircularProgressIndicator() },
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
fun CharacterCard(
    character: Character,
    modifier: Modifier = Modifier
) {
    OutlinedCard(modifier = modifier) {
        Text(
            modifier = Modifier.padding(Dimens.small),
            text = character.name
        )
    }
}

@Composable
fun ErrorIndicator(
    e: Exception,
    modifier: Modifier = Modifier,
    onRetry: () -> Unit,
) {
    Column(modifier = modifier) {
        Text(e.message ?: stringResource(R.string.unknown_error))
        OutlinedButton(onClick = onRetry) {
            Text(stringResource(R.string.retry))
        }
    }
}