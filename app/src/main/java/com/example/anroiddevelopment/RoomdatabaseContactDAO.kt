package com.example.anroiddevelopment

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


//RoomdatabaseContactDAO.kt (DAO stands for data Access objects) — defines what operations are allowed
//like here we used insert,update,delete,query
//This is your menu of allowed actions — same idea as your Retrofit interface from before. Each function here is annotated to tell Room exactly what SQL operation to perform behind the scenes:
//
//@Insert → generates SQL to add a new row
//@Update → generates SQL to modify an existing row (matched by primary key)
//@Delete → generates SQL to remove a row
//@Query("SELECT * FROM contact") → a raw SQL query you wrote yourself, asking for all rows in the contact table
//this is the second file that we need to create.
@Dao
interface RoomdatabaseContactDAO {

    @Insert
    suspend fun Insertcontact(contact : RoomDatabaseContact)

    @Update
    suspend fun Updatecontact(contact :RoomDatabaseContact)

    @Delete
    suspend fun Deletecontact(contact :RoomDatabaseContact)

    @Query("SELECT * FROM contact")
    fun getContact() : LiveData<List<RoomDatabaseContact>>
}