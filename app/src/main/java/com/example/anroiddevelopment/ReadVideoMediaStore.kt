package com.example.anroiddevelopment

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.MediaController
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.anroiddevelopment.databinding.ActivityReadVideoMediaStoreBinding

class ReadVideoMediaStore : AppCompatActivity() {

    private lateinit var binding: ActivityReadVideoMediaStoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityReadVideoMediaStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loadVideoButton.setOnClickListener {
            loadFirstVideo()
        }
    }

    private fun loadFirstVideo() {

        // Android 10+ uses VOLUME_EXTERNAL.
        val collection =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                MediaStore.Video.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
            } else {
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI
            }

        // We only need the video's ID.
        val projection = arrayOf(
            MediaStore.Video.Media._ID
        )

        // Read videos from MediaStore (newest first).
        val cursor = contentResolver.query(
            collection,
            projection,
            null,
            null,
            "${MediaStore.Video.Media.DATE_ADDED} DESC"
        )

        cursor?.use {

            // Move to the newest video.
            if (it.moveToFirst()) {

                // Get the column index of the ID.
                val idColumn =
                    it.getColumnIndexOrThrow(MediaStore.Video.Media._ID)

                // Read the video's ID.
                val videoId = it.getLong(idColumn)

                // Create the video's Uri.
                val videoUri = Uri.withAppendedPath(
                    collection,
                    videoId.toString()
                )

                // Add play, pause and seek controls.
                val controller = MediaController(this)
                controller.setAnchorView(binding.videoView)

                binding.videoView.setMediaController(controller)

                // Load the video into the VideoView.
                binding.videoView.setVideoURI(videoUri)

                // Start playing automatically.
                binding.videoView.start()

            } else {

                Toast.makeText(this, "No Videos Found", Toast.LENGTH_SHORT).show()

            }
        }
    }
}