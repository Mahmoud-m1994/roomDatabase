package com.example.shoppinglist.component

import androidx.compose.foundation.clickable
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector


@Composable
fun CardIcon(
    description: String,
    imageVector: ImageVector,
    imageVectorColor: Color = Color.Gray,
    onClick: () -> Unit
) {
    Icon(
        imageVector = imageVector,
        contentDescription = description,
        tint = imageVectorColor,
        modifier = Modifier.clickable(onClick = onClick)
    )
}