package com.example.anroiddevelopment

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Fragments : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Connect this activity with the XML layout.
        setContentView(R.layout.activity_fragments)

        // Connect the buttons from the XML using their IDs.
        val fragment1 = findViewById<Button>(R.id.fragment1)
        val fragment2 = findViewById<Button>(R.id.fragment2)

        // Create objects of both fragments.
        val firstFragment = FirstFragment()
        val secondFragment = SecondFragment()

        // Show the first fragment when the activity starts.
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.framefragment, firstFragment)
            commit()
        }

        // When the first button is clicked,
        // replace the current fragment with the FirstFragment.
        fragment1.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.framefragment, firstFragment)
                commit()
            }
        }

        // When the second button is clicked,
        // replace the current fragment with the SecondFragment.
        // addToBackStack() lets the user go back to the previous fragment
        // by pressing the Back button.
        fragment2.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.framefragment, secondFragment)
                addToBackStack(null)
                commit()
            }
        }
    }
}