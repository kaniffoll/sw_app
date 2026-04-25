package com.kaniffoll.swapplication.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.kaniffoll.swapplication.R
import com.kaniffoll.swapplication.ui.res.Dimens

@Composable
fun ErrorIndicator(
    e: Exception,
    modifier: Modifier = Modifier,
    onRetry: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Dimens.small2)
    ) {
        Text(e.message ?: stringResource(R.string.unknown_error))
        OutlinedButton(onClick = onRetry) {
            Text(stringResource(R.string.retry))
        }
    }
}