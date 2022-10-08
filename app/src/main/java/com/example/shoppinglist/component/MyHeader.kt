package com.example.shoppinglist.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun MyHeader(userExist: Boolean, userName: String) {
    val configuration = LocalConfiguration.current

    val headerHeight = (configuration.screenHeightDp / 6).dp
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(headerHeight)
            .background(MaterialTheme.colors.secondaryVariant),
    ) {
        Text(
            text = "Velkommen til ShoppingList",
            fontSize = 25.sp,
            fontFamily = FontFamily.SansSerif,
            fontStyle = FontStyle.Normal,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(6.dp)
        )
        if (userExist) {
            Text(
                text = userName,
                fontSize = 35.sp,
                fontFamily = FontFamily.SansSerif,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(6.dp)
            )
        }
    }
}