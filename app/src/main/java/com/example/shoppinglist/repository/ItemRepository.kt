package com.example.shoppinglist.repository

import com.example.shoppinglist.dao.ItemDao
import com.example.shoppinglist.models.Item
import kotlinx.coroutines.flow.Flow

class ItemRepository(private val itemDao: ItemDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    fun allItems(shoppingListId: Int): Flow<List<Item>> = itemDao.getAllItems(shoppingListId = shoppingListId)

    suspend fun insert(item: Item) {
        itemDao.insert(item)
    }

    suspend fun update(item: Item) {
        itemDao.update(item)
    }

    suspend fun delete(item: Item) {
        itemDao.delete(item)
    }

    suspend fun deleteAllItemsFromShoppingList(shoppingListId: Int) {
        itemDao.deleteAllItemsFromShoppingList(shoppingListId)
    }
}
