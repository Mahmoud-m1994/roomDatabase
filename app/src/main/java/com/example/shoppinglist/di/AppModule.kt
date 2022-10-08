package com.example.shoppinglist.di

import android.app.Application
import com.example.shoppinglist.dao.ItemDao
import com.example.shoppinglist.dao.ShoppingListDao
import com.example.shoppinglist.dao.UserDao
import com.example.shoppinglist.database.MyDatabase
import com.example.shoppinglist.repository.ItemRepository
import com.example.shoppinglist.repository.ShoppingListRepository
import com.example.shoppinglist.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    // User Provider Start
    @Singleton
    @Provides
    fun providerRepository(userDao: UserDao) : UserRepository {
        return UserRepository(userDao = userDao)
    }

    @Singleton
    @Provides
    fun provideUserDao(database: MyDatabase) : UserDao {
        return database.userDao()
    }
    // User Provider End

    // Item Provider Start
    @Singleton
    @Provides
    fun providerItemRepository(itemDao: ItemDao) : ItemRepository {
        return ItemRepository(itemDao = itemDao)
    }

    @Singleton
    @Provides
    fun provideItemDao(database: MyDatabase) : ItemDao {
        return database.itemDao()
    }
    // Item Provider End

    // ShoppingList Provider Start
    @Singleton
    @Provides
    fun providerShoppingListRepository(shoppingListDao: ShoppingListDao) : ShoppingListRepository {
        return ShoppingListRepository(shoppingListDao = shoppingListDao)
    }

    @Singleton
    @Provides
    fun provideShoppingListDao(database: MyDatabase) : ShoppingListDao {
        return database.shoppingListDao()
    }
    // ShoppingList Provider End

    // Database
    @Singleton
    @Provides
    fun provideDatabase(application: Application) : MyDatabase {
        return MyDatabase.getDatabase(application)
    }
}