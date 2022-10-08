package com.example.shoppinglist.viewmodels

import androidx.lifecycle.ViewModel
import com.example.shoppinglist.models.User
import com.example.shoppinglist.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

interface UserViewModelApi {
    val allUsers: Flow<List<User>>
    fun insert(user: User)
    fun update(user: User)
    fun delete(user: User)
    fun deleteAll()
}

@HiltViewModel
class UserViewModel
@Inject constructor (private val userRepository: UserRepository)
    : ViewModel(), UserViewModelApi
{
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    override val allUsers: Flow<List<User>> = userRepository.allUsers()

    override fun insert(user: User) {
        coroutineScope.launch {
            userRepository.insert(user)
        }
    }

    override fun update(user: User)  {
        coroutineScope.launch {
            userRepository.update(user)
        }
    }

    override fun delete(user: User)  {
        coroutineScope.launch {
            userRepository.delete(user)
        }
    }

    override fun deleteAll()  {
        coroutineScope.launch {
            userRepository.deleteAll()
        }
    }
}