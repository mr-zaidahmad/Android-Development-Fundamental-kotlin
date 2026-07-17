package com.example.anroiddevelopment

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HandlerScoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_handler_score)

        val tv = findViewById<TextView>(R.id.scoretext)

        // read the score that GameActivity sent us
        val score = intent.getIntExtra("score", 0)

        // show it on screen
        tv.text = getString(R.string.current_score, score)
    }
}