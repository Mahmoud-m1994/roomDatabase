package com.example.shoppinglist

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.shoppinglist.component.MainScreen
import com.example.shoppinglist.component.ShoppingListScreen
import com.example.shoppinglist.viewmodels.ItemViewModel
import com.example.shoppinglist.viewmodels.ShoppingListViewModel
import com.example.shoppinglist.viewmodels.UserViewModel

@Composable
fun Navigation(userViewModel: UserViewModel, shoppingListViewModel: ShoppingListViewModel,itemViewModel: ItemViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Activity.MainScreen.activityName) {
        composable(route = Activity.MainScreen.activityName) {
            MainScreen(
                userViewModel = userViewModel,
                shoppingListViewModel = shoppingListViewModel,
                navController = navController
            )
        }
        composable(
            route = Activity.ShoppingListScreen.activityName+"/{shoppingListId}",
            arguments = listOf(
                navArgument("shoppingListId") {
                    type = NavType.IntType
                }
            )
        ) {
            ShoppingListScreen(
                itemViewModel = itemViewModel,
                shoppingListId = it.arguments?.getInt("shoppingListId") ?: -1
            )
        }
    }
}