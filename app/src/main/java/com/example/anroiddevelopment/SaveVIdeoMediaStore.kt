package com.example.anroiddevelopment

import android.content.ContentValues
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.anroiddevelopment.databinding.ActivitySaveVideoMediaStoreBinding
import java.io.InputStream
import java.io.OutputStream

class SaveVideoMediaStore : AppCompatActivity() {

    private lateinit var binding: ActivitySaveVideoMediaStoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySaveVideoMediaStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveVideoButton.setOnClickListener {
            saveVideo()
        }
    }

    private fun saveVideo() {

        val values = ContentValues().apply {

            // Name shown in the Gallery.
            put(MediaStore.Video.Media.DISPLAY_NAME, "Video_${System.currentTimeMillis()}.mp4")

            // Type of the file.
            put(MediaStore.Video.Media.MIME_TYPE, "video/mp4")

            // Save inside Movies/MyApp (Android 10+).
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                put(MediaStore.Video.Media.RELATIVE_PATH, "Movies/MyApp")
            }
        }

        // Create an empty video entry in MediaStore.
        val uri = contentResolver.insert(
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
            values
        )

        uri?.let {

            // Read the video from res/raw.
            val inputStream: InputStream =
                resources.openRawResource(R.raw.sample)

            // Open the MediaStore file for writing.
            val outputStream: OutputStream? =
                contentResolver.openOutputStream(it)

            // Copy the video bytes into MediaStore.
            inputStream.copyTo(outputStream!!)

            inputStream.close()
            outputStream.close()

            Toast.makeText(this, "Video Saved", Toast.LENGTH_SHORT).show()
        }
    }
}