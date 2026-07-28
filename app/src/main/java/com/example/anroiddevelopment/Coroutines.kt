package com.example.anroiddevelopment

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.anroiddevelopment.databinding.ActivityCoroutinesBinding

class Coroutines : AppCompatActivity() {



    lateinit var binding: ActivityCoroutinesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding= ActivityCoroutinesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.SuspendFunction.setOnClickListener {
          startActivity(Intent(this, SuspendFunctionCoroutines ::class.java))
      }

        binding.CoroutinesBuilders.setOnClickListener {
            startActivity(Intent(this, CoroutinesBuilders::class.java))
        }
        binding.CoroutinesJobs.setOnClickListener {
            startActivity(Intent(this, CoroutinesJobs::class.java))
        }
        binding.Withcontext.setOnClickListener {
            startActivity(Intent(this, CoroutinesWithContext::class.java))
        }
        binding.Runblocking.setOnClickListener {
            startActivity(Intent(this, CoroutinesRunBlocking::class.java))
        }
    }


}