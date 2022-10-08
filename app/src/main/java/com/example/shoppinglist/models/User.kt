package com.example.shoppinglist.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @ColumnInfo(name = "username") val username: String,
)
