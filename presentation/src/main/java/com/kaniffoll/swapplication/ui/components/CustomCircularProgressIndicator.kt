package com.kaniffoll.swapplication.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kaniffoll.swapplication.ui.res.Dimens

@Composable
fun CustomCircularProgressIndicator(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxWidth().height(Dimens.progressIndicatorHeight)
    ) {
        CircularProgressIndicator()
    }
}