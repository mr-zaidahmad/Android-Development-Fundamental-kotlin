package com.example.anroiddevelopment

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.anroiddevelopment.databinding.ActivityRuntimePermissionBinding

class RuntimePermissionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRuntimePermissionBinding

    // Requests Camera permission.
    // cameraGranted will be true if the user taps "Allow",
    // otherwise it will be false.
    private val cameraPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { cameraGranted ->

            if (cameraGranted) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Permission Declined", Toast.LENGTH_SHORT).show()
            }
        }

    // Requests Location permission.
    private val locationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { locationGranted ->

            if (locationGranted) {
                Toast.makeText(this, "Location Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Location Declined", Toast.LENGTH_SHORT).show()
            }
        }

    // Requests Storage permission.
    // Android 11 & 12 -> READ_EXTERNAL_STORAGE
    // Android 13+ -> READ_MEDIA_IMAGES / READ_MEDIA_VIDEO / READ_MEDIA_AUDIO
    private val storagePermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { storageGranted ->

            if (storageGranted) {
                Toast.makeText(this, "Storage Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Storage Declined", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityRuntimePermissionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ---------------- CAMERA PERMISSION ----------------

        binding.CameraButton.setOnClickListener {

            // Check if the Camera permission has already been granted.
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CAMERA
                ) == PackageManager.PERMISSION_GRANTED
            ) {

                Toast.makeText(this, "Permission Already Granted", Toast.LENGTH_SHORT).show()

            } else {

                // Show the Camera permission dialog.
                cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }

        // ---------------- LOCATION PERMISSION ----------------

        binding.LocationPermission.setOnClickListener {

            // Check if the Location permission has already been granted.
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {

                Toast.makeText(this, "Location Already Granted", Toast.LENGTH_SHORT).show()

            } else {

                // Show the Location permission dialog.
                locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }

        // ---------------- STORAGE PERMISSION ----------------

        binding.storagePermission.setOnClickListener {

            // This is correct for Android 11 & Android 12.
            // If you test on Android 13 or newer, replace
            // READ_EXTERNAL_STORAGE with READ_MEDIA_IMAGES.
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            ) {

                Toast.makeText(this, "Storage Already Granted", Toast.LENGTH_SHORT).show()

            } else {

                // Show the Storage permission dialog.
                storagePermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }
    }
}




                  //If you want this app to support Android 11 and so on, replace the storage section with this:
//val storagePermission =
//    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
//        Manifest.permission.READ_MEDIA_IMAGES
//    } else {
//        Manifest.permission.READ_EXTERNAL_STORAGE
//    }
//
//if (ContextCompat.checkSelfPermission(
//        this,
//        storagePermission
//    ) == PackageManager.PERMISSION_GRANTED
//) {
//
//    Toast.makeText(this, "Storage Already Granted", Toast.LENGTH_SHORT).show()
//
//} else {
//
//    storagePermissionLauncher.launch(storagePermission)
//}