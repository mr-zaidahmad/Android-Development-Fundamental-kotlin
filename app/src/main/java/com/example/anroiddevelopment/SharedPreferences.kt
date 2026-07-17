package com.example.anroiddevelopment

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.anroiddevelopment.databinding.ActivitySharedPreferencesBinding



//SharedPreferences
//What it is: Android's original, oldest tool for saving small key-value pairs of data locally on the device — settings, flags, simple values.
//How it works: Saves data synchronously — meaning it can briefly freeze the app while saving, especially on slower devices.
//Example use: "Has the user seen the intro screen?" (true/false), a saved username, a theme preference.
//Downside: Can silently lose data if the app crashes mid-save; older, less safe by modern standards.
//Coroutines needed? No — works with plain, simple syntax.
class SharedPreferences : AppCompatActivity() {

    // Create a View Binding object so we can access views
    // without using findViewById().
    private lateinit var binding: ActivitySharedPreferencesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Connect this activity with the XML layout using View Binding.
        binding = ActivitySharedPreferencesBinding.inflate(layoutInflater)

        // Display the XML layout on the screen.
        setContentView(binding.root)

        // Run this code when the user clicks the button.
        binding.button2.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                // Get the text that the user typed in the EditText.
                val msg = binding.editText.text.toString()

                // Open (or create) a SharedPreferences file named "demo".
                val shrd = getSharedPreferences("demo", MODE_PRIVATE)

                // Create an editor so we can save data in SharedPreferences.
                val editor = shrd.edit()

                // Save the user's text using the key "str".
                editor.putString("str", msg)

                // Save the changes permanently.
                editor.apply()

                // Show the saved text in the TextView immediately.
                binding.textView3.text = msg
            }
        })

        // Open the same SharedPreferences file.
        val getShared = getSharedPreferences("demo", MODE_PRIVATE)

        // Read the value stored with the key "str".
        // If nothing is saved yet, show the default message instead.
        val value = getShared.getString(
            "str",
            "Save a note and it will show up here"
        )

        // Display the saved value (or the default message) in the TextView.
        binding.textView3.text = value
    }
}

