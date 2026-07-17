package com.example.anroiddevelopment

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date


//This tells Room: "Create a table named contact with three columns: id, name, phone."
// Every time you create a RoomDatabaseContact object, it represents one row in that table.
//this is the first file that we need to create.
@Entity(tableName = "contact")
data class RoomDatabaseContact(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name : String,
    val phone: String,
    val createdDate : Date)
