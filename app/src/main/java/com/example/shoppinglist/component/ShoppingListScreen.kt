package com.example.shoppinglist.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shoppinglist.models.Item
import com.example.shoppinglist.viewmodels.ItemViewModel

@Composable
fun ShoppingListScreen(itemViewModel: ItemViewModel, shoppingListId: Int) {
    var itemName by remember { mutableStateOf("") }
    var itemAmount by remember { mutableStateOf("") }
    var openDialog by remember { mutableStateOf(false) }
    var itemToUpdate by remember { mutableStateOf(Item(0, shoppingListId.toLong(), "", "")) }
    val items = itemViewModel.getAllItemsByListId(shoppingListId).collectAsState(initial = listOf())

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFF00BFA6))
                .border(width = 1.dp, color = Color.Black),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Shopping List id: $shoppingListId",
                fontStyle = FontStyle.Italic,
                fontSize = 24.sp,
            )
        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
            TextField(
                value = itemName,
                onValueChange = { itemName = it },
                label = { Text(text = "Name") },
                modifier = Modifier
                    .fillMaxWidth(0.3f)
            )
            TextField(
                value = itemAmount,
                onValueChange = { itemAmount = it },
                label = { Text(text = "Amount") },
                modifier = Modifier
                    .fillMaxWidth(0.4f)
            )
            Button(
                enabled = itemName.isNotBlank() && itemAmount.isNotBlank(),
                onClick = {
                    itemViewModel.insert(Item(
                        shoppingList_id = shoppingListId.toLong(),
                        item_name = itemName,
                        item_quantity = itemAmount
                    ))
                    itemName = ""
                    itemAmount = ""
                },
                modifier = Modifier
                    .fillMaxWidth(0.5f)
            )
            {
                Text(text = "Add")
            }
        }
        Divider(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp))

        if (items.value.isEmpty()) {
            Text(text = "No items in this list")
        } else {
            LazyColumn{
                items(items.value) { item ->
                    ItemCard(
                        item = item,
                        onDeleteClicked = { itemViewModel.delete(item) },
                        onEditClicked = {
                            openDialog = true
                            itemToUpdate = item
                        }
                    )
                }
            }
        }

        UpdateItem(
            openDialog = openDialog,
            item = itemToUpdate,
            itemViewModel = itemViewModel,
            onConfirm = {
                openDialog = false
            },
            onDismiss = { openDialog = false }
        )
    }
}
