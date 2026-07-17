package com.example.anroiddevelopment

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface RoomDatabaseEmployeeDAO {

    @Insert
    suspend fun insertEmployee(employee: RoomDatabaseEmployee)

    @Update
    suspend fun updateEmployee(employee: RoomDatabaseEmployee)
    @Delete
    suspend fun deleteEmployee(employee: RoomDatabaseEmployee)

    @Query("SELECT * FROM employee")
     fun getAllEmployee(): LiveData<List<RoomDatabaseEmployee>>

}