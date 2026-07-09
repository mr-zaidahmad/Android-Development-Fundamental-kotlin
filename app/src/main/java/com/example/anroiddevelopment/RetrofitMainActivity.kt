package com.example.anroiddevelopment

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitMainActivity : AppCompatActivity() {

    // Reference to our RecyclerView (the list container) from XML
    private lateinit var PostList: RecyclerView

    // Reference to our Adapter (the "factory worker" that fills each card with data)
    private lateinit var adapter: RetrofitPostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Load this screen's XML layout
        setContentView(R.layout.activity_retrofit_main)

        // Find the RecyclerView using its id from XML
        PostList = findViewById(R.id.PostList)

        // Tell it to arrange items in a simple vertical list (one below another)
        PostList.layoutManager = LinearLayoutManager(this)

        // Start fetching blog data as soon as the screen loads
        getData()
    }

    // This function handles the actual network request
    private fun getData() {

        // Get our ready-to-use connection, then call getPostList() on it
        val postList = BloggerAPI.getService().getPostList()

        // .enqueue() sends the request in the BACKGROUND, without freezing the app
        postList.enqueue(object : Callback<PostList> {

            // Runs automatically IF the request succeeds
            override fun onResponse(call: Call<PostList>, response: Response<PostList>) {

                // Unwrap the actual data from the response
                val list = response.body()

                if (list != null) {
                    // Build our adapter using the real list of posts we just received
                    adapter = RetrofitPostAdapter(this@RetrofitMainActivity, list.items)

                    // Attach the adapter to the RecyclerView -> this is what makes it show on screen
                    PostList.adapter = adapter
                }
            }

            // Runs automatically IF the request fails (no internet, bad key, server down, etc.)
            override fun onFailure(call: Call<PostList>, t: Throwable) {
                Toast.makeText(this@RetrofitMainActivity, "Error Occured", Toast.LENGTH_SHORT).show()
            }
        })
    }
}