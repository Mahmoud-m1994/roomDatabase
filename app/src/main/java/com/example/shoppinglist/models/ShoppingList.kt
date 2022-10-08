package com.example.shoppinglist.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_list_table")
data class ShoppingList (
    @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @ColumnInfo(name = "name") val shoppingList_name: String,
    @ColumnInfo(name = "created_by") val created_by: Int,
    @ColumnInfo(name = "shared_with") val shared_with: Int?,
    @ColumnInfo(name = "write_access") val access_type: Boolean?,
)