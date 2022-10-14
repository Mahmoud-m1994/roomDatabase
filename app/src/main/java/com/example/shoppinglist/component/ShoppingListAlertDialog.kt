package com.example.shoppinglist.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.shoppinglist.R
import com.example.shoppinglist.models.ShoppingList
import com.example.shoppinglist.viewmodels.ShoppingListViewModel


@Composable
fun ShoppingListAlertDialog(
    openDialog: Boolean,
    user_id: Long?,
    shoppingListViewModel: ShoppingListViewModel,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    val shoppingListName = remember { mutableStateOf("") }
    val enabled = remember { mutableStateOf(shoppingListName.value.isNotBlank()) }
    if (openDialog) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = { Text(text = stringResource(R.string.create_shopping_list_title)) },
            text = {
                Spacer(modifier = Modifier.padding(8.dp))
                TextField(
                    value = shoppingListName.value,
                    onValueChange = {
                        shoppingListName.value = it
                        enabled.value = it.isNotBlank()
                    },
                    label = { Text(text = stringResource(R.string.enter_shopping_list_name)) }
                )
            },
            confirmButton = {
                Button( onClick = {
                    if (user_id != null) {
                        shoppingListViewModel.insert(
                            ShoppingList(
                                shoppingList_name = shoppingListName.value.trim(),
                                created_by = user_id.toInt(),
                                shared_with = null,
                                access_type = null
                            )
                        )
                    }
                    shoppingListName.value = ""
                    onConfirm()
                }, colors = ButtonDefaults.buttonColors(backgroundColor =if (enabled.value) MaterialTheme.colors.secondaryVariant else Color.LightGray)) {
                    Text(text = "Create")
                }
            },
            dismissButton = {
                Button(onClick = { onDismiss() }) {
                    Text(text = "Cancel")
                }
            }
        )
    }
}