package com.example.anroiddevelopment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast


class LongPress : Fragment() {
   private lateinit var longPressbutton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_long_press, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        longPressbutton=view.findViewById<Button>(R.id.longPressbuttonn)
        longPressbutton.setOnLongClickListener {
            Toast.makeText(requireContext(),"LongPress Triggered", Toast.LENGTH_SHORT).show()
            true
        }
    }
}