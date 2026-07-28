package com.example.anroiddevelopment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.anroiddevelopment.databinding.FragmentFirebaseRealtimeDatabaseMainScreenBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase



//on mainfragment we made two button and we navigate it through navgraph.
class FirebaseRealtimeDatabaseMainScreen : Fragment() {
   lateinit var binding: FragmentFirebaseRealtimeDatabaseMainScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        //this is how the binding is passed when we use fragments.
        binding= FragmentFirebaseRealtimeDatabaseMainScreenBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //now to navigate between one fragment to another we don't need to use intent cuz intent is for mavigating through
        //activity but here we are navigation through fragments so we use findNavController().navigate() and the
        //id from which fragment are we navigating.
        binding.insertdata.setOnClickListener {
          findNavController().navigate(R.id.action_firebaseRealtimeDatabaseMainScreen_to_firebaseRealtimeDatabaseInsertData)

        }
        binding.fetchdata.setOnClickListener {
          findNavController().navigate(R.id.action_firebaseRealtimeDatabaseMainScreen_to_firebaseRealtimeDatabaseFetchData)
        }
    }

}
