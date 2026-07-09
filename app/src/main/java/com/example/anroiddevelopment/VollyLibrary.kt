package com.example.anroiddevelopment

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class VollyLibrary : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_volly_library)
        Volleylibrary()   //calling the function

    }
    private fun Volleylibrary() {
        // Find the TextView from your XML layout using its id
        val textView = findViewById<TextView>(R.id.text)

        // Create a queue (like a to-do list) that will handle our network request
        // "this" means the current activity
        val queue = Volley.newRequestQueue(this)

        // This is the website address we want to get data from
        val url = "https://www.google.com"


        val stringRequest = StringRequest(  //String request is a ready made tool from volley
            //which is used to ex[ect the answer(response) from website Since we just want text
            // back from Google, we use StringRequest

            Request.Method.GET, url,  //This tells the request what kind of action you want to do GET or Post?
            //GET fetch the data from website and POST means submit the data to the website.
            //and  url lastly define Just the website address you want to talk to — same as what you type in a browser

            //now we tell if our websites get successfully run then what to do and if our website get
            //not succesfully run what to do then?
            //first we run the success one
            Response.Listener<String> { response ->
                // Display the first 500 characters of the response string in a textView.
                textView.text = "Response is: ${response.substring(0, 500)}"
            },
            //now we tell what to do if the website doesn't run.
            Response.ErrorListener { textView.text = "That didn't work!" })

        //// Finally, send the request by adding it to the queue
        // Without this line, nothing will actually happen
        queue.add(stringRequest)
    }
}

