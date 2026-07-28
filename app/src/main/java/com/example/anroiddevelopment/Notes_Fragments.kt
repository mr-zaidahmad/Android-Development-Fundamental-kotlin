package com.example.anroiddevelopment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class Notes_Fragments : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val notesView= inflater.inflate(R.layout.fragment_notes__fragments, container, false)
        val notesTextRedirect=notesView.findViewById<TextView>(R.id.NotesFragments)

        val username=arguments?.getString("username")
        notesTextRedirect.text="Notesfragment \nusername : $username"

        notesTextRedirect.setOnClickListener {
            var bundle= Bundle()
            bundle.putString("username",username)
        }


        return notesView
    }

}