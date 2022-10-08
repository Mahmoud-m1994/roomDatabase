package com.example.shoppinglist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shoppinglist.dao.ItemDao
import com.example.shoppinglist.dao.ShoppingListDao
import com.example.shoppinglist.dao.UserDao
import com.example.shoppinglist.models.Item
import com.example.shoppinglist.models.ShoppingList
import com.example.shoppinglist.models.User

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = [User::class, ShoppingList::class, Item::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun shoppingListDao(): ShoppingListDao
    abstract fun itemDao(): ItemDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getDatabase(context: Context): MyDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}