package com.example.anroiddevelopment

import android.app.Application
import android.util.Log




//The Application class is the first component Android creates when your app starts.
//Only one Application object exists while your app is running.
//It is used for app-wide initialization, such as Firebase, Room, Crashlytics, and Analytics.
//onCreate() runs only once when the app starts.
//You must register it in AndroidManifest.xml using android:name.
//It is not used for UI. Activities and Fragments are responsible for screens

class ApplicationClass : Application() {

    override fun onCreate() {
        super.onCreate()

        Log.d("Zaid_TEST","Application started")
    }
}