package com.example.shoppinglist.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shoppinglist.R
import com.example.shoppinglist.models.User
import com.example.shoppinglist.viewmodels.UserViewModel


@Composable
fun CreateUserCard(userViewModel: UserViewModel) {
    val name = remember { mutableStateOf("") }
    var enabled by remember { mutableStateOf(name.value.isNotBlank()) }
    Column(
        modifier = Modifier
            .padding(16.dp)
            .shadow(
                4.dp,
                spotColor = Color.Black,
                ambientColor = Color.Gray
            )
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = stringResource(R.string.add_new_user),
                fontSize = 40.sp,
                fontFamily = FontFamily.SansSerif,
                fontStyle = FontStyle.Italic
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = name.value,
                onValueChange = {
                    name.value = it
                    enabled = it.isNotBlank()
                },
                label = { Text(text = stringResource(R.string.enter_username)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Button(
                enabled = enabled,
                onClick = { userViewModel.insert(User(username = name.value)) },
                colors = ButtonDefaults.buttonColors(backgroundColor = if (enabled) MaterialTheme.colors.secondaryVariant else Color.LightGray)
            ) {
                Text(text = stringResource(R.string.create_user))
            }
        }
    }
}
