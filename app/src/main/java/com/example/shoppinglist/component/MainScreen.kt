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
import androidx.navigation.NavController
import com.example.shoppinglist.Activity
import com.example.shoppinglist.viewmodels.ShoppingListViewModel
import com.example.shoppinglist.viewmodels.UserViewModel


@Composable
fun MainScreen(userViewModel: UserViewModel, shoppingListViewModel: ShoppingListViewModel, navController: NavController) {
    val users = userViewModel.allUsers.collectAsState(initial = listOf())
    var userId : Long = 0
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
        if (users.value.isEmpty() || users.value.firstOrNull() == null) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                CreateUserCard(userViewModel = userViewModel)
            }
        } else {
            userId = users.value.firstOrNull()?.id!!
            val shoppingLists =
                users.value.first().id?.toInt()
                    ?.let { shoppingListViewModel.getAllShoppingList(it).collectAsState(initial = listOf()) }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LazyColumn {
                    if (shoppingLists != null) {
                        items(shoppingLists.value.size) { user ->
                            ShoppingListCard(
                                shoppingList = shoppingLists.value[user],
                                onDeleteClicked = {
                                    shoppingLists.value[user].id?.let { it1 ->
                                        shoppingListViewModel.deleteShoppingList(
                                            it1.toInt(), userId.toInt())
                                    }
                                    openDialog = false
                                },
                                onEditClicked = {
                                    openDialog = true
                                },
                                onCardClicked = {
                                    navController.navigate(Activity.ShoppingListScreen.getArg(shoppingLists.value[user].id?.toInt() ?: -1))
                                }
                            )
                        }
                    }
                }
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.End
                ) {
                    FloatingActionButton(
                        onClick = {
                            openDialog = true
                        },
                        backgroundColor = Color(0xFF00BFA6)
                    ) {
                        Icon(Icons.Filled.Add, contentDescription = "Add")
                    }
                }
            }
        }
        ShoppingListAlertDialog(
            openDialog = openDialog,
            onDismiss = { openDialog = false },
            shoppingListViewModel = shoppingListViewModel,
            user_id = userId,
            onConfirm = { openDialog = false }
        )
    }
}