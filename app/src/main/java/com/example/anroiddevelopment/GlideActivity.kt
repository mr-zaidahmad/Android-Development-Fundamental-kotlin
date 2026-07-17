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
            getString(R.string.https_cdn_pixabay_com_photo_2017_04_16_20_42_brain_2235771_340_png),
            getString(R.string.https_cdn_pixabay_com_photo_2017_04_16_20_42_light_bulb_2235770_340_png)
        )
        // Glide.with(this) -> start Glide using current screen as context
        // .load(urls[0]) -> tells Glide which image link to load
        // .into(img1) -> tells Glide where to display that image
        Glide.with(this).load(urls[0]).into(img1)  // load first image into img1
        Glide.with(this).load(urls[1]).into(img2)  // load second image into img2




    }
}

