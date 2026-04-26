package com.kaniffoll.swapplication.ui.screens.character

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CharacterDetails(
    id: Int,
    provideTitle: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    provideTitle("Hello from details")
    Text("DETAILS")
}