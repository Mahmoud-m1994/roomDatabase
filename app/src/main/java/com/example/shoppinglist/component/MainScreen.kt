package com.example.shoppinglist.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.shoppinglist.models.ShoppingList
import com.example.shoppinglist.viewmodels.ShoppingListViewModel
import com.example.shoppinglist.viewmodels.UserViewModel


@Composable
fun MainScreen(userViewModel: UserViewModel, shoppingListViewModel: ShoppingListViewModel) {
    val users = userViewModel.allUsers.collectAsState(initial = listOf())
    var openDialog by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        Column(modifier = Modifier.clip(shape = RoundedCornerShape(
            bottomStart = 16.dp,
            bottomEnd = 16.dp
        )
        )) {
            MyHeader(users.value.isNotEmpty(), users.value.firstOrNull()?.username ?: "")
        }
        if (users.value.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                CreateUserCard(userViewModel = userViewModel)
            }
        } else {
            val shoppingLists =
                users.value.first().id?.toInt()
                    ?.let { shoppingListViewModel.getAllShoppingList(it).collectAsState(initial = listOf()) }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End
            ) {
                LazyColumn {
                    if (shoppingLists != null) {
                        items(shoppingLists.value.size) { user ->
                            ShoppingListCard(shoppingLists.value[user])
                        }
                    }
                }
                FloatingActionButton(
                    onClick = {
                        openDialog = true
                    },
                    backgroundColor = Color.Green,
                    contentColor = Color.White
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                }
            }
        }
        ShoppingListAlertDialog(
            openDialog = openDialog,
            onDismiss = { openDialog = false },
            onConfirm = {
                val userId = users.value.first().id?.toInt()
                val userName = users.value.first().username
                if (userId != null) {
                    shoppingListViewModel.insert(
                        ShoppingList(
                            shoppingList_name = userName,
                            created_by = userId,
                            shared_with = null,
                            access_type = null
                        )
                    )
                }
                openDialog = false
            }
        )
    }
}