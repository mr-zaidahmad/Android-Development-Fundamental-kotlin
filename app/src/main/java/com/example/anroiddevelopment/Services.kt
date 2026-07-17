package com.example.anroiddevelopment

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.anroiddevelopment.databinding.ActivityServicesBinding



//An Android Service is a component that performs work in the background without showing any user interface.
//Examples
//🎵 Playing music while you're using another app.
//📥 Downloading a file in the background.
//📍 Tracking your location while the screen is off.
//🔄 Syncing data with a server.
//these are all perform by services
class Services : AppCompatActivity() {

    lateinit var  binding : ActivityServicesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityServicesBinding.inflate(layoutInflater)
         setContentView(binding.root)

        binding.StartService.setOnClickListener {
            startService(Intent(this, LoggerServices::class.java))
        }
        binding.StopService.setOnClickListener {
             stopService(Intent(this, LoggerServices ::class.java))
        }

    }
}