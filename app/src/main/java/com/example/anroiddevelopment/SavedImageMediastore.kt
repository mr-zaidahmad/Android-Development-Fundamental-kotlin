package com.example.anroiddevelopment

import android.Manifest
import android.content.ContentValues
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.anroiddevelopment.databinding.ActivitySavedImageMediastoreBinding
import java.io.OutputStream

class SavedImageMediastore : AppCompatActivity() {

    private lateinit var binding: ActivitySavedImageMediastoreBinding

   private var camerpermissionLauchner=registerForActivityResult(ActivityResultContracts.RequestPermission()){
       granted ->
       if (granted){
           Toast.makeText(this,"Permission Granted", Toast.LENGTH_SHORT).show()
       }else{
           Toast.makeText(this,"Permission Declined", Toast.LENGTH_SHORT).show()
       }
   }

    private var cameraLauncher=registerForActivityResult(ActivityResultContracts.TakePicturePreview()){
        bitmap ->
        if (bitmap!=null){
            binding.PictureView.setImageBitmap(bitmap)
            saveImage(bitmap)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
         binding= ActivitySavedImageMediastoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cameraButton.setOnClickListener {
        if (ContextCompat.checkSelfPermission(
            this,
                Manifest.permission.CAMERA
        )== PackageManager.PERMISSION_GRANTED
        ){
            cameraLauncher.launch(null)
        }else{
            camerpermissionLauchner.launch(Manifest.permission.CAMERA)
        }
        }
    }
    private fun saveImage(bitmap: Bitmap) {

        // Stores information about the image before saving it.
        val values = ContentValues().apply {

            // Name of the image that will appear in the Gallery.
            put(MediaStore.Images.Media.DISPLAY_NAME, "Image_${System.currentTimeMillis()}.jpg")

            // Tells Android the type of file being saved.
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")

            // Android 10+ : Save the image inside Pictures/MyApp.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/MyApp")
            }
        }

        // Ask MediaStore to create a new empty image file.
        val uri = contentResolver.insert(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            values
        )

        uri?.let {

            // Open the file so we can write image data into it.
            val outputStream: OutputStream? = contentResolver.openOutputStream(it)

            outputStream?.use { stream ->

                // Write the Bitmap into the file as a JPEG image.
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            }

            Toast.makeText(this, "Image Saved to Gallery", Toast.LENGTH_SHORT).show()
        }
    }
}