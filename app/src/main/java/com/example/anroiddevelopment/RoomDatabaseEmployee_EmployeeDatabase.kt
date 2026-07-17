package com.example.anroiddevelopment

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Tells Room that this is a database.
// It contains one table: RoomDatabaseEmployee.
// Version 1 means this is the first version of our database.
@Database(entities = [RoomDatabaseEmployee::class], version = 1)
abstract class RoomDatabaseEmployee_EmployeeDatabase : RoomDatabase() {

    // Gives access to the DAO.
    // Whenever we want to insert, update, delete, or read data,
    // we'll use this DAO.
    abstract fun employeeDAO(): RoomDatabaseEmployeeDAO

    companion object {

        // Stores a single instance of the database.
        // Initially, it is null because the database hasn't been created yet.
        private var INSTANCE: RoomDatabaseEmployee_EmployeeDatabase? = null

        // Returns the database instance.
        // If it doesn't exist, create it.
        // If it already exists, return the existing one.
        fun getDatabase(context: Context): RoomDatabaseEmployee_EmployeeDatabase {

            if (INSTANCE == null) {

                // Creates the Room database.
                INSTANCE = Room.databaseBuilder(

                    // Application context.
                    context,

                    // The database class we are creating.
                    RoomDatabaseEmployee_EmployeeDatabase::class.java,

                    // Name of the database stored on the phone.
                    "employeeDB"

                ).build()
            }

            // Return the database instance.
            return INSTANCE!!
        }
    }
}