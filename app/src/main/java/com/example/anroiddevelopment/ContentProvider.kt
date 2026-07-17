package com.example.anroiddevelopment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.anroiddevelopment.databinding.ActivityContentProviderBinding
import java.io.File
import java.io.FileOutputStream

// This Activity demonstrates a very simple use of a Content Provider.
// We are not creating our own Content Provider.
// Instead, we use Android's built-in FileProvider to safely share
// an image from our app with other apps like WhatsApp or Gmail.
class ContentProvider : AppCompatActivity() {

    // ViewBinding variable.
    // After inflating the layout, this variable gives us direct access
    // to every view inside activity_content_provider.xml.
    private lateinit var binding: ActivityContentProviderBinding

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the XML layout using ViewBinding.
        binding = ActivityContentProviderBinding.inflate(layoutInflater)

        // Display the layout on the screen.
        setContentView(binding.root)

        // cacheDir is a temporary folder inside our application.
        // We create a new file named "android_file.png" inside that folder.
        // This is the image file that we will share later.
        val path = File(cacheDir, getString(R.string.android_file_png))

        // Open the drawable image stored in res/drawable.
        // It returns an InputStream so we can read the image data.
        val inputStream = resources.openRawResource(R.drawable.android_logo)

        // Create an OutputStream that writes data into the file
        // we created inside the cache folder.
        val outputStream = FileOutputStream(path)

        // Copy every byte from the drawable image into our cache file.
        // After this line, android_file.png exists inside cacheDir.
        inputStream.copyTo(outputStream)

        // Always close streams after using them.
        // This releases the resources and avoids memory leaks.
        inputStream.close()
        outputStream.close()

        // When the Share button is clicked...
        binding.Share.setOnClickListener {

            // FileProvider converts our private File into a secure content:// URI.
            // Other apps cannot directly access files inside our app,
            // so Android requires us to share them through FileProvider.
            val uri = FileProvider.getUriForFile(
                this,

                // packageName returns our application's package name automatically.
                // If our package is:
                // com.example.anroiddevelopment
                //
                // then this becomes:
                // com.example.anroiddevelopment.provider
                //
                // This authority MUST exactly match the authority written
                // inside AndroidManifest.xml.
                getString(R.string.provider, packageName),

                // The file we want to share.
                path
            )

            // Create an implicit intent.
            // ACTION_SEND tells Android:
            // "I want to send/share some data to another app."
            val intent = Intent(Intent.ACTION_SEND)

            // Tell Android that the data we are sharing is a PNG image.
            intent.type = getString(R.string.image_png)

            // EXTRA_STREAM is used whenever we share a file.
            // Here we attach the image URI to the intent.
            intent.putExtra(Intent.EXTRA_STREAM, uri)

            // Since the receiving app doesn't normally have permission
            // to read files inside our app,
            // we temporarily grant read permission.
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

            // Display Android's share sheet.
            // The user can choose Gmail, WhatsApp, Telegram,
            // Bluetooth, Drive, etc.
            startActivity(
                Intent.createChooser(
                    intent,
                    getString(R.string.share_image)
                )
            )
        }
    }
}