# README #

## **Android RoomDatabase, MVVM - pattern with dependency injection and Compose** ##


## *Screenrecord* ##
[ShoppingList-DB.webm](https://user-images.githubusercontent.com/70547966/195814808-91a55057-5089-4911-993a-ebd40ab3baa1.webm)

## *Overview* ##

* Shopping List allow user create several folders, and in each folder the use can create, update and delete items
* The project is build with MVVM architecture pattern
* It used Android roomdatabase to store data, and Compose to build UI

## *Database model, ER diagram* ##
<img src="./app/src/main/res/drawable/er_diagram.png" width=400 height=350>

Entities in shopping_list_db
* user_table (<u>id</u>, username)
* shopping_list_table (<u>id</u>, shoppingListName,created_by(FK), shared_with(FK), write_access)
* item_table (<u>id</u>, shoppingList_id(FK), item_name, item_quantity)

The relationship bweteen user_table and sopping_list_tabke is one-to-many. Which means that user can own 0 or as many lists he/she want. The same is for relationship between shopping_list and item_table.

In shopping_list_table i have two foreign keys:

1. created_by: it inserts automaticlly when user create a shoppingList folder
2. shared_with: represent the user_id that the creator share shoppingList with. It's hasn't been implemented since i use local/roomdatabase.

In item_table i have just one foreign key --> the shopping_list_id

## *MVVM pattern* ##
The main reason for using mvvm architecture is to have a clean code and It's easier for me to controll dataflow in each part. Therefor I created 4 packages:

1. **models**: hosts data classes to all objects I used. In this case just the entites
2. **dao**: dao class for each entity to take a request from repository and send queries to my DB
3. **repository**: separate data and connect viewmodel to both Database and webservice.
4. **viewmodels**: It's as service-class in server-client model. Which means take a request from the client/UI and give back a response whenever the data came from db

To reduce complexity and dependency between all layers. I used Hilt Dagger. In this way I controll all instances in one single file.


## *Compose* ##
As mentioned early in this file I used compose to build UI. Compose makes our live really easy in Android. Now we can create components and reuse them whenever/whereever we want. E.g ItemCard, CardIcon ... and so on in Shooping List project.

* Some Examples
```kotlin

@Composable
fun CardIcon(
    description: String,
    imageVector: ImageVector,
    imageVectorColor: Color = Color.Gray,
    onClick: () -> Unit
) {
    Icon(
        imageVector = imageVector,
        contentDescription = description,
        tint = imageVectorColor,
        modifier = Modifier.clickable(onClick = onClick)
    )
}

@Composable
fun ItemCard(
    item: Item,
    onDeleteClicked: (Item) -> Unit,
    onEditClicked: (Item) -> Unit
) {
    ...
}
```
To make the app preformance even better I used LazyColumn to render the items from db such as shoppingList items
```kotlin
LazyColumn{
    items(items.value) { item ->
        ItemCard(
            item = item,
            onDeleteClicked = { itemViewModel.delete(item) },
            onEditClicked = {
                openDialog = true
                itemToUpdate = item
            }
        )
    }
}
```
I wanted to avoid sending unnecessary request to my database. Therefor I enable Update Item button as showen in Video JUST when the data aren't same as It's in database. In my case not a big deal because my app hasn't very high network traffic, but It's really helpful in big projects

```kotlin
enabled = itemName.isNotBlank() && itemQuantity.isNotBlank() && 
(itemName != item.item_name || itemQuantity != item.item_quantity)
```