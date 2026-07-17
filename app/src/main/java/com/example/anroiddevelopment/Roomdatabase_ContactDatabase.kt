    package com.example.anroiddevelopment

    import android.content.Context
    import androidx.room.Database
    import androidx.room.InvalidationTracker
    import androidx.room.Room
    import androidx.room.RoomDatabase
    import androidx.room.TypeConverters

    //This ties everything together — it says: "This database contains
    //one table, built from RoomDatabaseContact, and here's how you access it — through contactDAO()."
    //this is the third file that we need to create

    //Roomdatabase_ContactDatabase.kt — the actual database container
    @Database(entities = [RoomDatabaseContact::class], version = 1)
    @TypeConverters(RoomDatabaseConvertors::class)
    abstract class Roomdatabase_ContactDatabase : RoomDatabase(){
        abstract fun contactDAO() : RoomdatabaseContactDAO

        companion object{
            @Volatile
            private var INSTANCE : Roomdatabase_ContactDatabase?=null

            fun getdatabase(context: Context): Roomdatabase_ContactDatabase{
                if (INSTANCE == null){
                    synchronized(this){
                        INSTANCE=Room.databaseBuilder(context.applicationContext,
                            Roomdatabase_ContactDatabase::class.java,
                            "ContactDB").build()
                    }
                }
                return INSTANCE!!
            }
        }
    }