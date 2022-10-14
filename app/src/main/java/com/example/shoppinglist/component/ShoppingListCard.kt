package com.example.shoppinglist.component

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.dp
import com.example.shoppinglist.R
import com.example.shoppinglist.models.ShoppingList


@Composable
fun ShoppingListCard(
    shoppingList: ShoppingList,
    onDeleteClicked: (ShoppingList) -> Unit,
    onEditClicked: (ShoppingList) -> Unit,
    onCardClicked: (ShoppingList) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = { onCardClicked(shoppingList) }),
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            Text(shoppingList.shoppingList_name)
        }
        Row(horizontalArrangement = Arrangement.End) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                CardIcon(
                    description = stringResource(R.string.edit_shopping_list),
                    imageVector = Icons.Rounded.Edit,
                    onClick = { onEditClicked(shoppingList) }
                )
                CardIcon(
                    description = stringResource(R.string.delete_shopping_List),
                    imageVector = Icons.Rounded.Delete,
                    imageVectorColor = Color.Red,
                    onClick = { onDeleteClicked(shoppingList) }
                )
            }
        }
    }
}
