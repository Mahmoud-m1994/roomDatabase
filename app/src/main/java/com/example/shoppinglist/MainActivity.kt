package com.example.shoppinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.shoppinglist.ui.theme.ShoppingListTheme
import com.example.shoppinglist.viewmodels.ItemViewModel
import com.example.shoppinglist.viewmodels.ShoppingListViewModel
import com.example.shoppinglist.viewmodels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userViewModel : UserViewModel by viewModels()
        val shoppingListViewModel : ShoppingListViewModel by viewModels()
        val itemViewModel : ItemViewModel by viewModels()
        setContent {
            ShoppingListTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Navigation(
                        userViewModel = userViewModel,
                        shoppingListViewModel = shoppingListViewModel,
                        itemViewModel = itemViewModel
                    )
                }
            }
        }
    }
}