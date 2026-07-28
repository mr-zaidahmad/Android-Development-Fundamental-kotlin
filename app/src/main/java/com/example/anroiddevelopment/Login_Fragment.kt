package com.example.anroiddevelopment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController


class Login_Fragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val loginview=inflater.inflate(R.layout.fragment_login_, container, false)
        val loginTextRedirect=loginview.findViewById<TextView>(R.id.LoginFragment)

        val username=arguments?.getString("username")
        loginTextRedirect.text = "Login Fragment\nUsername: $username"

        loginTextRedirect.setOnClickListener {

            // Create a new Bundle
            val bundle = Bundle()

            // Put the same username into it
            bundle.putString("username", username)

            // Navigate to Main Fragment and send the Bundle
            findNavController().navigate(
                R.id.action_login_Fragment_to_main_Fragment,
                bundle
            )
        }
        return loginview
    }
}