package com.example.anroiddevelopment

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.anroiddevelopment.databinding.ActivityBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import android.widget.Button
import android.widget.Toast

class BottomSheet : AppCompatActivity() {
    private lateinit var binding: ActivityBottomSheetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding= ActivityBottomSheetBinding.inflate(layoutInflater)
        setContentView(binding.root)

      binding.BottomSheetButton.setOnClickListener {

          val dialog= BottomSheetDialog(this)

          val view= LayoutInflater.from(this).inflate(R.layout.bottom_sheet_item,null)

          dialog.setContentView(view)

          view.findViewById<Button>(R.id.Editbutton).setOnClickListener {
              Toast.makeText(this,"Edit Button Clicked", Toast.LENGTH_SHORT).show()
              dialog.dismiss()
          }
          view.findViewById<Button>(R.id.deletebutton).setOnClickListener {
              Toast.makeText(this,"Delete Button Clicked", Toast.LENGTH_SHORT).show()
              dialog.dismiss()
          }
          view.findViewById<Button>(R.id.sharebutton).setOnClickListener {
              Toast.makeText(this,"Share Button Clicked", Toast.LENGTH_SHORT).show()
              dialog.dismiss()
          }

          dialog.show()
      }
    }
}