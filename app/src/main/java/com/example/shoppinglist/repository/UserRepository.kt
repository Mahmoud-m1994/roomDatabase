package com.example.shoppinglist.repository

import com.example.shoppinglist.dao.UserDao
import com.example.shoppinglist.models.User
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    fun allUsers(): Flow<List<User>> = userDao.getAllUsers()

    suspend fun insert(user: User) {
        userDao.insert(user)
    }

    suspend fun update(user: User) {
        userDao.update(user)
    }

    suspend fun delete(user: User) {
        userDao.delete(user)
    }

    suspend fun deleteAll() {
        userDao.deleteAll()
    }
}