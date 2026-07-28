package com.example.anroiddevelopment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.anroiddevelopment.databinding.FragmentFirebaseRealtimeDatabaseInsertDataBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FirebaseRealtimeDatabaseInsertData : Fragment() {

    lateinit var binding: FragmentFirebaseRealtimeDatabaseInsertDataBinding

    // Accessing the Firebase Realtime Database.
    lateinit var datareference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // View Binding for Fragment.
        binding = FragmentFirebaseRealtimeDatabaseInsertDataBinding.inflate(
            layoutInflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Insert button click.
        binding.InsertDataButton.setOnClickListener {
            SavePatientData()
        }

        // Reference to Patients node.
        datareference = FirebaseDatabase
            .getInstance()
            .getReference("Patients")
    }

    private fun SavePatientData() {

        // Getting values from EditTexts.
        val drugname = binding.DrugName.text.toString().trim()
        val drugdiscription = binding.DrugDiscription.text.toString().trim()
        val sideeffect = binding.SideEffect.text.toString().trim()
        val prevention = binding.Prevention.text.toString().trim()
        val treatment = binding.Treatment.text.toString().trim()

        // Validation.
        if (drugname.isEmpty()) {
            binding.DrugName.error = "Please enter drug name"
            binding.DrugName.requestFocus()
            return
        }

        if (drugdiscription.isEmpty()) {
            binding.DrugDiscription.error = "Please enter drug description"
            binding.DrugDiscription.requestFocus()
            return
        }

        if (sideeffect.isEmpty()) {
            binding.SideEffect.error = "Please enter side effect"
            binding.SideEffect.requestFocus()
            return
        }

        if (prevention.isEmpty()) {
            binding.Prevention.error = "Please enter prevention"
            binding.Prevention.requestFocus()
            return
        }

        if (treatment.isEmpty()) {
            binding.Treatment.error = "Please enter treatment"
            binding.Treatment.requestFocus()
            return
        }

        // Firebase generates a unique ID for every patient.
        val patientID = datareference.push().key!!

        // Create patient object.
        val patient = RealtimeDatabasePatientData(
            patientID,
            drugname,
            drugdiscription,
            sideeffect,
            prevention,
            treatment
        )

        // Save object in Firebase.
        datareference.child(patientID)
            .setValue(patient)
            .addOnSuccessListener {

                Toast.makeText(
                    requireActivity(),
                    "Data Added Successfully",
                    Toast.LENGTH_SHORT
                ).show()

                // Clear all fields.
                binding.DrugName.text.clear()
                binding.DrugDiscription.text.clear()
                binding.SideEffect.text.clear()
                binding.Prevention.text.clear()
                binding.Treatment.text.clear()

            }
            .addOnFailureListener { err ->

                Toast.makeText(
                    requireActivity(),
                    err.message,
                    Toast.LENGTH_SHORT
                ).show()

            }
    }
}