package com.example.anroiddevelopment

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.anroiddevelopment.databinding.ActivityEncryptedSharedPreferenceBinding
import androidx.core.content.edit

class EncryptedSharedPreference : AppCompatActivity() {

    private lateinit var binding: ActivityEncryptedSharedPreferenceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityEncryptedSharedPreferenceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val masterkey= MasterKey.Builder(this).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()

        val encryptedSharedPreference= EncryptedSharedPreferences.create(
            this,
            "SecurePreferences",
            masterkey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )


        binding.Encryptedsavebuttion.setOnClickListener {
                     encryptedSharedPreference.edit {
                         putString("username", binding.Encryptededittext.text.toString())
                     }
            binding.Encryptededittext.setText("")
            Toast.makeText(this,"saved", Toast.LENGTH_LONG).show()

        }

        binding.EncryptedgetButton.setOnClickListener {
              val value=encryptedSharedPreference.getString("username","No data")

            binding.Encryptededittext.setText(value)
        }

        binding.EncryptedclearButton.setOnClickListener {
                  encryptedSharedPreference.edit {
                      clear()
                  }
            binding.Encryptededittext.setText("")

            Toast.makeText(this,"Cleared", Toast.LENGTH_SHORT).show()

        }

    }
}