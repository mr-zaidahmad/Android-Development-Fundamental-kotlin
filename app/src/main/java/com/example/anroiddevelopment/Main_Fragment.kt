package com.example.anroiddevelopment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController


class Main_Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val mainview=inflater.inflate(R.layout.fragment_main_, container, false)
        val mainTextRedirect=mainview.findViewById<TextView>(R.id.MainFragment)

        val username=arguments?.getString("username")
        mainTextRedirect.text = "Main Fragment\nUsername: $username"

        mainTextRedirect.setOnClickListener {
            var bundle= Bundle()

            bundle.putString("username",username)

            findNavController().navigate(R.id.action_main_Fragment_to_notes_Fragments,bundle)

        }
        return mainview
    }

}