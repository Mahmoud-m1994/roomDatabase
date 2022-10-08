package com.example.shoppinglist.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_table")
data class Item (
    @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @ColumnInfo(name = "shoppingList_id") val shoppingList_id: Long,
    @ColumnInfo(name = "item_name") val item_name: String?,
    @ColumnInfo(name = "item_quantity") val item_quantity: String?,
)
