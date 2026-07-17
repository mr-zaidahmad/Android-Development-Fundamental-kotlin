package com.example.anroiddevelopment

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employee")
 data class RoomDatabaseEmployee (
     @PrimaryKey(autoGenerate = true)
     var id: Int=0,
     var name: String,
     var department : String

)