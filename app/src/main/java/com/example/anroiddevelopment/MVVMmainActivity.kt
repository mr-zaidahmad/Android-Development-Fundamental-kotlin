package com.example.anroiddevelopment

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import com.example.anroiddevelopment.databinding.ActivityMvvmmainBinding

class MVVMmainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMvvmmainBinding
    private val viewmodel: MVVMViewModel by viewModels ()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityMvvmmainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textView.text=viewmodel.getcount().toString()

        binding.button.setOnClickListener {

            val value= viewmodel.increment()

            binding.textView.text=value.toString()
        }
    }
}