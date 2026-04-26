package com.kaniffoll.swapplication.ui.screens.character

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
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
    var query by remember { mutableStateOf("") }

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
        item {
            SWSearchBar(
                query = query,
                onQueryChange = { query = it },
                onSearch = { viewModel.search(query) }
            )
        }

        items(
            paginationState.allItems!!,
        ) { item ->
            CharacterCard(character = item, onClick = onCardClick)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SWSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = query,
        onValueChange = onQueryChange,
        trailingIcon = {
            Icon(
                painter = painterResource(R.drawable.baseline_search_24),
                contentDescription = null,
                modifier = Modifier.clickable(onClick = onSearch)
            )
        }
    )
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