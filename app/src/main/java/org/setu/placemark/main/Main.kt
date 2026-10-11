package org.setu.placemark.main

import org.setu.placemark.models.PlacemarkMemStore
import org.setu.placemark.models.PlacemarkModel

val store = PlacemarkMemStore()

fun main() {

    println("=== Placemark Console App (Lab 1) ===")

    var input: Int

    do {

        input = menu()

        when (input) {
            1 -> addPlacemark()
            2 -> listPlacemarks()
            3 -> updatePlacemark()
            4 -> deletePlacemark()
            5 -> searchPlacemark()
            0 -> println("\nExiting Placemark application. Goodbye!")
            else -> println("\nInvalid option. Please try again.")
        }

    } while (input != 0)
}

fun menu(): Int {
    println("\n----------------------------------")
    println(" MAIN MENU")
    println("----------------------------------")
    println(" 1. Add Placemark")
    println(" 2. List All Placemarks")
    println(" 3. Update a Placemark")
    println(" 4. Delete a Placemark")
    println(" 5. Search Placemark by ID")
    println(" 0. Exit")
    print("\nEnter option: ")

    // An exhausted input stream should finish the program, not repeat the menu.
    val option = readlnOrNull() ?: return 0
    return option.trim().toIntOrNull() ?: -1
}

fun addPlacemark() {

    println("\n--- Add Placemark ---")

    print("Enter Title: ")
    val title = readlnOrNull()?.trim().orEmpty()

    print("Enter Description: ")
    val description = readlnOrNull()?.trim().orEmpty()

    if (title.isNotEmpty()) {

        val placemark = PlacemarkModel(
            title = title,
            description = description
        )

        store.create(placemark)

        println("Placemark added successfully with ID: ${placemark.id}")

    } else {
        println("Title cannot be empty. Creation cancelled.")
    }
}

fun listPlacemarks() {
    println("\n--- All Placemarks ---")
    val placemarks = store.findAll()

    if (placemarks.isEmpty()) {
        println("No placemarks stored yet.")
        return
    }

    placemarks.forEach {
        println("ID: ${it.id} | Title: ${it.title} | Description: ${it.description}")
    }
}

fun updatePlacemark() {
    println("\n--- Update Placemark ---")
    listPlacemarks()
    if (store.findAll().isEmpty()) return

    print("\nEnter ID of Placemark to update: ")
    val id = readlnOrNull()?.trim()?.toLongOrNull()
    if (id == null) {
        println("Enter a valid numeric ID.")
        return
    }

    val existing = store.findOne(id)
    if (existing == null) {
        println("Placemark with ID $id not found.")
        return
    }

    print("Enter New Title: ")
    val title = readlnOrNull()?.trim().orEmpty()
    print("Enter New Description: ")
    val description = readlnOrNull()?.trim().orEmpty()

    if (title.isEmpty()) {
        println("Title cannot be empty. Update cancelled.")
        return
    }

    // Keep the original ID and change only the fields entered by the user.
    if (store.update(existing.copy(title = title, description = description))) {
        println("Placemark updated successfully.")
    }
}

fun deletePlacemark() {
    println("\n--- Delete Placemark ---")
    listPlacemarks()
    if (store.findAll().isEmpty()) return

    print("\nEnter ID of Placemark to delete: ")
    val id = readlnOrNull()?.trim()?.toLongOrNull()
    if (id == null) {
        println("Invalid ID entered.")
        return
    }

    if (store.delete(id)) {
        println("Placemark with ID $id deleted successfully.")
    } else {
        println("Placemark with ID $id not found.")
    }
}

fun searchPlacemark() {
    println("\n--- Search Placemark ---")
    print("Enter ID: ")
    val id = readlnOrNull()?.trim()?.toLongOrNull()
    if (id == null) {
        println("Invalid ID entered.")
        return
    }

    val placemark = store.findOne(id)
    if (placemark == null) {
        println("No placemark found with ID $id.")
    } else {
        println("Found: ID: ${placemark.id} | Title: ${placemark.title} | Description: ${placemark.description}")
    }
}
