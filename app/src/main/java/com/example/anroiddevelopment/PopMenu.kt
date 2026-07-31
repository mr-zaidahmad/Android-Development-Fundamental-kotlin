package com.example.anroiddevelopment

import android.os.Bundle
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.anroiddevelopment.databinding.ActivityPopMenuBinding

class PopMenu : AppCompatActivity() {

    private lateinit var binding: ActivityPopMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPopMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.popupMenu.setOnClickListener {

            val popupMenu = PopupMenu(this, binding.popupMenu)

            popupMenu.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)

            popupMenu.setOnMenuItemClickListener {

                when (it.itemId) {

                    R.id.edit -> {
                        Toast.makeText(this, "Edit Clicked", Toast.LENGTH_SHORT).show()
                        true
                    }

                    R.id.delete -> {
                        Toast.makeText(this, "Delete Clicked", Toast.LENGTH_SHORT).show()
                        true
                    }

                    else -> false
                }
            }

            popupMenu.show()
        }
    }
}