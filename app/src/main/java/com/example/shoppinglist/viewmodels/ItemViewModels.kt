package com.example.shoppinglist.viewmodels

import androidx.lifecycle.ViewModel
import com.example.shoppinglist.models.Item
import com.example.shoppinglist.repository.ItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

interface ItemViewModelApi {
    fun getAllItemsByListId(listId: Int): Flow<List<Item>>
    fun insert(item: Item)
    fun update(item: Item)
    fun delete(item: Item)
    fun deleteAllItemsFromShoppingList(listId: Int)
}

@HiltViewModel
class ItemViewModel
@Inject constructor (private val itemRepository: ItemRepository)
    : ViewModel(), ItemViewModelApi
{
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    override fun getAllItemsByListId(listId: Int): Flow<List<Item>> = itemRepository.allItems(listId)

    override fun insert(item: Item) {
        coroutineScope.launch {
            itemRepository.insert(item)
        }
    }

    override fun update(item: Item)  {
        coroutineScope.launch {
            itemRepository.update(item)
        }
    }

    override fun delete(item: Item)  {
        coroutineScope.launch {
            itemRepository.delete(item)
        }
    }

    override fun deleteAllItemsFromShoppingList(listId: Int)  {
        coroutineScope.launch {
            itemRepository.deleteAllItemsFromShoppingList(listId)
        }
    }
}