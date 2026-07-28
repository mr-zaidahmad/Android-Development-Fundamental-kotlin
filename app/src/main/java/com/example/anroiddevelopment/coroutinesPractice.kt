package com.example.anroiddevelopment

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class coroutinesPractice : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_coroutines_practice)

        val button=findViewById<Button>(R.id.coroutinespractice)
        button.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                runOnUiThread {
                    Toast.makeText(this@coroutinesPractice, "Hello", Toast.LENGTH_SHORT).show()
                }
            }
        }
        }
    }
