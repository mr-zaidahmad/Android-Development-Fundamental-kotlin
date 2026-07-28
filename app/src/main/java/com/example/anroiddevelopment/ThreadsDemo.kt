package com.example.anroiddevelopment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class ThreadsDemo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_threads_demo)

        val textView1=findViewById<TextView>(R.id.BackgroundThreadStatus)
        val button1=findViewById<Button>(R.id.backgroundThread)
        val textView2=findViewById<TextView>(R.id.MaintThreadStatus)
        val button2=findViewById<Button>(R.id.MainThread)



     //using this it will crash the app for 10 seconds cux we are running it on the main thread.
        button2.setOnClickListener {

            Thread.sleep(10000)

            textView2.text = "Finished"
        }

        //and this it will not crashed cuz the task is running in the background thread.
//        button.setOnClickListener {
//
//            Thread {
//
//                Thread.sleep(10000)
//
//                runOnUiThread {
//                    textView.text = "Finished"
//                }
//
//            }.start()
//        }


        //now this will also works but we uses the handler here cuz it's the right way to use it
        //the reason we use handler cuz we have flexibility in it we can use postdelayed instead of that sleep so
        //it will delayed the thread of how much u want.
        button1.setOnClickListener {

            Thread {
                Handler(Looper.getMainLooper()).postDelayed( {
                    textView1.text = "Finished"
                },5000)
            }.start()
        }
    }
}