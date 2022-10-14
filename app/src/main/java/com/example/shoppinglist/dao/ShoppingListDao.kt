package com.example.shoppinglist.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.shoppinglist.models.ShoppingList
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingListDao {

    @Insert
    suspend fun insert(shoppingList: ShoppingList)

    // update shopping list name
    @Query("UPDATE shopping_list_table SET name = :shoppingListName WHERE id = :shoppingListId AND created_by = :createdBy")
    suspend fun updateShoppingListName(shoppingListId: Int, shoppingListName: String, createdBy: Int)

    @Query("UPDATE shopping_list_table SET shared_with = :sharedWith WHERE id = :shoppingListId AND created_by = :createdBy")
    suspend fun shareWith(
        shoppingListId: Int,
        createdBy: Int,
        sharedWith: Int
    )

    @Query("SELECT * FROM shopping_list_table WHERE created_by = :userId OR shared_with = :userId ORDER BY id DESC")
    fun getAllShoppingList(userId: Int): Flow<List<ShoppingList>>

    @Query("SELECT * FROM shopping_list_table WHERE created_by LIKE :userId")
    fun getAllShoppingListCreatedByUser(userId: Int): Flow<List<ShoppingList>>

    @Query("SELECT * FROM shopping_list_table WHERE shared_with LIKE :userId")
    fun getAllShoppingListSharedWithByUser(userId: Int): Flow<List<ShoppingList>>

    @Query("DELETE FROM shopping_list_table WHERE id = :shoppingListId AND created_by = :createdBy")
    suspend fun deleteShoppingList(shoppingListId: Int, createdBy: Int)
}