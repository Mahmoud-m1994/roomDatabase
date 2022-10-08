package com.example.shoppinglist.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.shoppinglist.models.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)

    @Query("SELECT * FROM item_table WHERE id = :shoppingListId")
    fun getAllItems(shoppingListId: Int): Flow<List<Item>>

    @Update
    suspend fun update(item: Item)

    @Delete
    suspend fun delete(item: Item)

    @Query("DELETE FROM item_table WHERE id = :shoppingListId")
    suspend fun deleteAllItemsFromShoppingList(shoppingListId: Int)
}