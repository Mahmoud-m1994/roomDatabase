package com.example.shoppinglist.repository

import com.example.shoppinglist.dao.ShoppingListDao
import com.example.shoppinglist.models.ShoppingList
import kotlinx.coroutines.flow.Flow

class ShoppingListRepository(private val shoppingListDao: ShoppingListDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    fun allShoppingLists(userId : Int): Flow<List<ShoppingList>> = shoppingListDao.getAllShoppingList(userId = userId)

    fun getAllShoppingListCreatedByUser(userId: Int): Flow<List<ShoppingList>> = shoppingListDao.getAllShoppingListCreatedByUser(userId = userId)

    fun getAllShoppingListSharedWithByUser(userId: Int): Flow<List<ShoppingList>> = shoppingListDao.getAllShoppingListSharedWithByUser(userId = userId)

    suspend fun insert(shoppingList: ShoppingList) {
        shoppingListDao.insert(shoppingList)
    }

    // update shopping list name
    suspend fun updateShoppingListName(shoppingListId: Int, shoppingListName: String, createdBy: Int) {
        shoppingListDao.updateShoppingListName(shoppingListId, shoppingListName, createdBy)
    }

    suspend fun shareWith(shoppingListId: Int, createdBy: Int, sharedWith: Int) {
        shoppingListDao.shareWith(shoppingListId, createdBy, sharedWith)
    }

    suspend fun deleteShoppingList(shoppingListId: Int, createdBy: Int) {
        shoppingListDao.deleteShoppingList(shoppingListId, createdBy)
    }
}
