package com.example.anroiddevelopment

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Fragments : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fragments)

        val fragment1 = findViewById<Button>(R.id.fragment1)
        val fragment2 = findViewById<Button>(R.id.fragment2)

         var firstfragment= FirstFragment()
        var secondfragment= SecondFragment()


        supportFragmentManager.beginTransaction().apply{
            replace(R.id.framefragment,firstfragment)
            addToBackStack(null)
            commit()
        }

            fragment1.setOnClickListener{
                supportFragmentManager.beginTransaction().apply{
                    replace(R.id.framefragment,firstfragment)
                    commit()
                }
            }
        fragment2.setOnClickListener{
            supportFragmentManager.beginTransaction().apply{
                replace(R.id.framefragment,secondfragment)
                addToBackStack(null)
                commit()
            }
        }
    }

}