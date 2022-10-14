package com.example.shoppinglist

sealed class Activity(val activityName: String) {
    object MainScreen : Activity("main_screen")
    object ShoppingListScreen : Activity("shopping_list_screen")

    fun getArg(vararg args: Int) : String {
        val output = buildString {
            append(activityName)
            args.forEach { item ->
                append("/$item")
            }
        }
        return output
    }

}
