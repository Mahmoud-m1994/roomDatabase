# README #

## **Android RoomDatabase, MVVM - pattern, Compose and dependency injection** ##
***
## *Screenrecord* ##
Comming soon

## *Overview* ##

* Shopping List allow user create several folders, and in each folder the use can create, update and delete items
* The project is build with MVVM architecture pattern
* It used Android roomdatabase to store data, and Compose to build UI

## *Database model, ER diagram* ##
Entities in shopping_list_db
* user_table (<u>id</u>, username)
* shopping_list_table (<u>id</u>, shoppingListName,created_by(FK), shared_with(FK), write_access)
* item_table (<u>id</u>, shoppingList_id(FK), item_name, item_quantity)

The relationship bweteen user_table and sopping_list_tabke is one-to-many. Which means that user can own 0 or as many lists he/she want. The same is for relationship between shopping_list and item_table

![ER_database](app/src/main/res/drawable/Skjermbilde 2022-10-14 kl. 11.03.56.png)