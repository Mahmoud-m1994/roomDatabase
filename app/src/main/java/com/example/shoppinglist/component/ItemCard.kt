package com.example.shoppinglist.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.shoppinglist.R
import com.example.shoppinglist.models.Item

@Composable
fun ItemCard(
    item: Item,
    onDeleteClicked: (Item) -> Unit,
    onEditClicked: (Item) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            item.item_name?.let { Text(it) }
            item.item_quantity?.let { Text("$it item", fontFamily = FontFamily.Cursive) }
        }
        Row(horizontalArrangement = Arrangement.End) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                CardIcon(
                    description = stringResource(R.string.edit_shopping_List_item),
                    imageVector = Icons.Rounded.Edit,
                    onClick = { onEditClicked(item) }
                )
                CardIcon(
                    description = stringResource(R.string.delete_shopping_List_item),
                    imageVector = Icons.Rounded.Delete,
                    imageVectorColor = Color.Red,
                    onClick = { onDeleteClicked(item) }
                )
            }
        }
    }
}