package com.example.anroiddevelopment

import androidx.room.TypeConverter
import java.util.Date

class RoomDatabaseConvertors {

    @TypeConverter
    fun fromDateToLong(value: Date): Long{
  return value.time
    }

    @TypeConverter
    fun fromLongToDate(value : Long): Date{
    return Date(value)
    }
}