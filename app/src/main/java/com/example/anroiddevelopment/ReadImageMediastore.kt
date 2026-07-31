package com.example.anroiddevelopment

import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.anroiddevelopment.databinding.ActivityReadImageMediastoreBinding

class ReadImageMediaStore : AppCompatActivity() {

    private lateinit var binding: ActivityReadImageMediastoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityReadImageMediastoreBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.loadImageButton.setOnClickListener {

            loadFirstImage()

        }
    }

    private fun loadFirstImage() {

        // We only want these two columns from MediaStore.
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME
        )

        // Ask MediaStore to return all images stored on the phone.
        val cursor = contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            "${MediaStore.Images.Media.DATE_ADDED} DESC"
        )

        cursor?.use {

            // Move to the first image.
            if (it.moveToFirst()) {

                // Get the column index of the image ID.
                val idColumn =
                    it.getColumnIndexOrThrow(MediaStore.Images.Media._ID)

                // Read the image ID.
                val imageId = it.getLong(idColumn)

                // Create the Uri of that image.
                val imageUri = Uri.withAppendedPath(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    imageId.toString()
                )

                // Display the image.
                binding.imageView.setImageURI(imageUri)

                Toast.makeText(this, "Image Loaded", Toast.LENGTH_SHORT).show()

            } else {

                Toast.makeText(this, "No Images Found", Toast.LENGTH_SHORT).show()

            }
        }
    }
}