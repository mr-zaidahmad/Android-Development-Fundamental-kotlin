package com.example.anroiddevelopment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)
        //Calling handler
        // Handler lets us delay some code from running immediately
        // Looper.getMainLooper() means "run this on the main/UI thread" this is imp to
        //pass it to other other thread once the outout is  denerated then call the
        //main thread which all is done by handler
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)  //usong intent to call mainactivity
            startActivity(intent) //calling intent
            finish() // closes SplashActivity so user can't go back to it.
            //we want splash so we give finsih so that it just rin one time at the start then cleses
            //on it's own
        }, 3000) //here we give how much time u want to run it i give 3000 mili seconds which is
         //3 seconds.

    }
}