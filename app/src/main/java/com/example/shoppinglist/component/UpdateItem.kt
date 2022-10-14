package com.example.shoppinglist.component

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shoppinglist.models.Item
import com.example.shoppinglist.viewmodels.ItemViewModel


@Composable
fun UpdateItem(
    openDialog: Boolean,
    item: Item,
    itemViewModel: ItemViewModel,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    var itemName by remember { mutableStateOf("") }
    var itemQuantity by remember { mutableStateOf("") }

    item.item_name?.let { itemName = it }
    item.item_quantity?.let { itemQuantity = it }

    if (openDialog) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = { Text(text = "Update ${item.item_name}") },
            text = {
                Column(verticalArrangement = Arrangement.Center, modifier = Modifier.padding(8.dp)) {
                    Spacer(modifier = Modifier.padding(8.dp))
                    TextField(
                        value = itemName,
                        onValueChange = { itemName = it },
                        label = { Text(text = "Item Name") }
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    TextField(
                        value = itemQuantity,
                        onValueChange = { itemQuantity = it },
                        label = { Text(text = "Item Quantity") }
                    )
                }
            },
            confirmButton = {
                Button(
                    enabled = itemName.isNotBlank() && itemQuantity.isNotBlank() && (itemName != item.item_name || itemQuantity != item.item_quantity),
                    onClick = {
                        Log.d("item", "UpdateItem: ${item.item_name} updated to $itemName")
                        itemViewModel.update(
                            Item(
                                id = item.id,
                                item_name = itemName,
                                item_quantity = itemQuantity,
                                shoppingList_id = item.shoppingList_id
                            )
                        )
                        onConfirm()
                }, colors = ButtonDefaults.buttonColors(backgroundColor =MaterialTheme.colors.secondaryVariant)) {
                    Text(text = "Update")
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