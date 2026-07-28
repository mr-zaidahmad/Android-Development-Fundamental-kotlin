package com.example.anroiddevelopment

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat



//for using the navigation fragment we must have to make an activity that access the fragment and in it's xml
// we denote the containerview in which we pass main mainfragment like this
//app:navGraph="@navigation/realtime_database_nav"

class FirebaseRealtimeDatabase : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_firebase_realtime_database)

    }
}