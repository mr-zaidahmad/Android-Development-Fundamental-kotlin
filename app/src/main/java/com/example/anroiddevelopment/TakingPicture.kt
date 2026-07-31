package com.example.anroiddevelopment

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.anroiddevelopment.databinding.ActivityTakingPictureBinding


class TakingPicture : AppCompatActivity() {
    private lateinit var binding: ActivityTakingPictureBinding

    private var cameraPermissionLauncher=registerForActivityResult(ActivityResultContracts.RequestPermission()){
        cameraGranted->
        if (cameraGranted){
            cameraLauncher.launch(null)
        }
        else{
            Toast.makeText(this,"Permission Declined", Toast.LENGTH_SHORT).show()
        }
    }
    val cameraLauncher=registerForActivityResult(ActivityResultContracts.TakePicturePreview()){
        bitmap : Bitmap? ->
        if (bitmap !=null){
             binding.ImageViewing.setImageBitmap(bitmap)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityTakingPictureBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.Camerapermission.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                this,
                    Manifest.permission.CAMERA
            )== PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"Permission Already Granted", Toast.LENGTH_SHORT).show()

            }else{
                cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }
       binding.OwnCameraPermission.setOnClickListener {

           if (ContextCompat.checkSelfPermission(
                   this,
                   Manifest.permission.CAMERA
               ) == PackageManager.PERMISSION_GRANTED
           ) {
               startCamera()
           } else {
               cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
           }
       }
    }

    private fun startCamera() {
        val cameraProviderFuture= ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener(
            {
                val cameraProvider=cameraProviderFuture.get()

                val preview= Preview.Builder().build()
                preview.surfaceProvider =binding.PreviewView.surfaceProvider

                val cameraselector= CameraSelector.DEFAULT_BACK_CAMERA

                cameraProvider.unbindAll()

                cameraProvider.bindToLifecycle(
                    this,
                    cameraselector,
                    preview
                )
            }, ContextCompat.getMainExecutor(this))
    }
}