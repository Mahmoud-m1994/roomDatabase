package com.example.shoppinglist.viewmodels

import androidx.lifecycle.ViewModel
import com.example.shoppinglist.models.ShoppingList
import com.example.shoppinglist.repository.ShoppingListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

interface ShoppingListViewModelApi {
    fun getAllShoppingList(userId : Int) : Flow<List<ShoppingList>>
    fun getAllShoppingListCreatedByUser(userId : Int) : Flow<List<ShoppingList>>
    fun getAllShoppingListSharedWithUser(userId : Int) : Flow<List<ShoppingList>>
    fun insert(shoppingList: ShoppingList)
    fun updateShoppingListName(shoppingListId: Int, shoppingListName: String, createdBy: Int)
    fun shareShoppingList(shoppingListId: Int, createdBy: Int, sharedWith: Int)
    fun deleteShoppingList(shoppingListId: Int, createdBy: Int)
}

@HiltViewModel
class ShoppingListViewModel
@Inject constructor (private val shoppingListRepository: ShoppingListRepository)
    : ViewModel(), ShoppingListViewModelApi
{
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    override fun getAllShoppingList(userId : Int) : Flow<List<ShoppingList>> = shoppingListRepository.allShoppingLists(userId)

    override fun getAllShoppingListCreatedByUser(userId : Int) : Flow<List<ShoppingList>> = shoppingListRepository.getAllShoppingListCreatedByUser(userId)

    override fun getAllShoppingListSharedWithUser(userId : Int) : Flow<List<ShoppingList>> = shoppingListRepository.getAllShoppingListSharedWithByUser(userId)


    override fun insert(shoppingList: ShoppingList) {
        coroutineScope.launch {
            shoppingListRepository.insert(shoppingList)
        }
    }

    override fun updateShoppingListName(shoppingListId: Int, shoppingListName: String, createdBy: Int) {
        coroutineScope.launch {
            shoppingListRepository.updateShoppingListName(shoppingListId, shoppingListName, createdBy)
        }
    }

    override fun shareShoppingList(shoppingListId: Int, createdBy: Int, sharedWith: Int) {
        coroutineScope.launch {
            shoppingListRepository.shareWith(shoppingListId, createdBy, sharedWith)
        }
    }

    override fun deleteShoppingList(shoppingListId: Int, createdBy: Int) {
        coroutineScope.launch {
            shoppingListRepository.deleteShoppingList(shoppingListId, createdBy)
        }
    }
}