package com.example.anroiddevelopment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController

class regiser_fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Load the Register Fragment layout
        val registerView =
            inflater.inflate(R.layout.fragment_regiser_fragment, container, false)

        // Find the EditText and Button from the XML
        val username = registerView.findViewById<EditText>(R.id.Username)
        val saveButton = registerView.findViewById<Button>(R.id.btnSave)

        // Run this code when the Save button is clicked
        saveButton.setOnClickListener {

            // Read the text entered by the user

            val name = username.text.toString()

            // Create a Bundle to carry data
            val bundle = Bundle()

            // Store the username inside the Bundle
            // "username" is the key
            // name is the value entered by the user
            bundle.putString("username", name)

            // Open the Login Fragment and send the Bundle
            findNavController().navigate(
                R.id.action_regiser_fragment_to_login_Fragment,
                bundle
            )
        }

        // Return the layout to Android
        return registerView
    }
}