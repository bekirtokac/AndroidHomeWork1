package com.bekirtokac.homework1

open class Item(val name: String, val price: Double)

class Food(name: String, price: Double, val weight: String) : Item(name, price)

class Cloth(name: String, price: Double, val type: String) : Item(name, price)

class ShoppingList {
    private val itemList = mutableListOf<Item>()

    fun addItem(itemType: Int, itemName: String, itemPrice: Double, itemInfo: String) {
        when (itemType) {
            1 -> itemList.add(Food(itemName, itemPrice, itemInfo))
            2 -> itemList.add(Cloth(itemName, itemPrice, itemInfo))
            else -> println("Invalid item type.")
        }
        println("Item added successfully.")
    }

    fun displayItems() {
        if (itemList.isEmpty()) {
            println("Shopping list is empty.")
            return
        }
        println("Shopping List:")
        for ((index, item) in itemList.withIndex()) {
            when (item) {
                is Food -> println("${index + 1}) ${item.name} - \$${item.price} - Weigth: ${item.weight}kg")
                is Cloth -> println("${index + 1}) ${item.name} - \$${item.price} - Type: ${item.type}")
            }
        }
    }

    fun deleteItem(itemNumber: Int) {
        if (itemNumber !in 1..itemList.size) {
            println("Invalid item number.")
            return
        }
        itemList.removeAt(itemNumber - 1)
        println("Item deleted successfully.")
    }
}

fun main(args: Array<String>) {
    val shoppingList = ShoppingList()

    while (true) {
        println("\nSelect an option:")
        println("1) Add Item")
        println("2) Display Items")
        println("3) Delete Item")
        println("4) Exit")
        print("Your Choice Is: ")

        when (readlnOrNull()?.toIntOrNull() ?: 0) {
            1 -> {
                println("Select item type:\n1) Food\n2) Cloth")
                print("Your Choice Is: ")
                val itemType = readlnOrNull()?.toIntOrNull() ?: 0
                if (itemType !in 1..2) {
                    println("Invalid choice. Please select 1 for Food or 2 for Cloth.")
                    continue
                }

                print("Enter item name: ")
                val itemName = readlnOrNull() ?: ""
                print("Enter item price: ")
                val itemPrice = readlnOrNull()?.toDoubleOrNull() ?: 0.0

                val itemInfo = if (itemType == 1) {
                    print("Enter how many kilograms you want to add: ")
                    readlnOrNull() ?: ""
                } else {
                    print("Enter type: ")
                    readlnOrNull() ?: ""
                }

                shoppingList.addItem(itemType, itemName, itemPrice, itemInfo)
            }
            2 -> shoppingList.displayItems()
            3 -> {
                println("Select the item number you want to delete:")
                shoppingList.displayItems()
                print("Your Choice Is: ")
                val itemNumber = readlnOrNull()?.toIntOrNull() ?: 0
                shoppingList.deleteItem(itemNumber)
            }
            4 -> {
                println("Exiting...")
                return
            }
            else -> println("Invalid choice. Please select a number from 1 to 4.")
        }
    }
}
