package com.example.anroiddevelopment

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class BroadCastReciever : AppCompatActivity() {

    val mybroad= MyBroad()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_broad_cast_reciever)

    }

    override fun onStart() {
        super.onStart()
        val intentfilter= IntentFilter()
        intentfilter.addAction(Intent.ACTION_POWER_CONNECTED)
        intentfilter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        registerReceiver(mybroad,intentfilter)

        val intent= Intent(this, MyBroad::class.java)
        sendBroadcast(intent)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(mybroad)
    }
}