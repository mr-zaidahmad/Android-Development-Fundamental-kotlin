package com.example.anroiddevelopment

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// "object" instead of "class" -> we only ever want ONE shared instance of this,
// since there's no reason to have multiple separate connections to the same API
object BloggerAPI {

    // Your unique API key - proves to Google you're allowed to access this blog's data
    private const val key = "AIzaSyCyPXNhZHK2JkEGwSeaU5pRwOyM4pXW9tE"

    // The base address for your specific blog
    // NOTE: must end with "/" or Retrofit will crash
    private const val url = "https://www.googleapis.com/blogger/v3/blogs/5959029328798708357/posts/"

    // This will hold our built connection once created, so we don't rebuild it every time
    private var postService: PostService? = null

    // This function gives us a ready-to-use connection
    fun getService(): PostService {

        // Only build the connection if it doesn't already exist
        if (postService == null) {
            val retrofit = Retrofit.Builder()
                .baseUrl(url)                                    // where to send requests
                .addConverterFactory(GsonConverterFactory.create())  // auto-converts JSON -> Kotlin objects
                .build()

            // Create a working version of our "menu" (interface below)
            postService = retrofit.create(PostService::class.java)
        }

        // Return the ready connection
        return postService!!
    }

    // This interface is like a MENU - it describes what requests are available
    interface PostService {

        // @GET means we're asking for data (not sending anything)
        // "?key=$key" gets added to the end of the base URL automatically
        @GET("?key=$key")
        fun getPostList(): Call<PostList>   // Call<PostList> = "I expect a PostList back, eventually"
    }
}