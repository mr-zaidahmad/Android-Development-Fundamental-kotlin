package com.example.anroiddevelopment

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import kotlin.collections.get

class GlideActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_glide)

        // Find the two ImageViews from the XML using their id
        val img1 = findViewById<ImageView>(R.id.img1)
        val img2 = findViewById<ImageView>(R.id.img2)
        // Store all the image links (URLs) we want to load
        // Must be direct image links (ending in .png/.jpg), not webpage links
        val urls = arrayOf(  //taking array and storing imagges in it.
            "https://cdn.pixabay.com/photo/2017/04/16/20/42/brain-2235771__340.png",
            "https://cdn.pixabay.com/photo/2017/04/16/20/42/light-bulb-2235770__340.png"
        )
        // Glide.with(this) -> start Glide using current screen as context
        // .load(urls[0]) -> tells Glide which image link to load
        // .into(img1) -> tells Glide where to display that image
        Glide.with(this).load(urls[0]).into(img1)  // load first image into img1
        Glide.with(this).load(urls[1]).into(img2)  // load second image into img2




    }
}

