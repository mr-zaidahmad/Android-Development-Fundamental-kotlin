package com.example.anroiddevelopment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.anroiddevelopment.databinding.FragmentFirebaseRealtimeDatabaseDetailsBinding
import com.google.firebase.database.FirebaseDatabase

class FirebaseRealtimeDatabaseDetails : Fragment() {

    lateinit var binding: FragmentFirebaseRealtimeDatabaseDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFirebaseRealtimeDatabaseDetailsBinding.inflate(
            layoutInflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Show all received values.
        setValuesToViews()

        // Update button.
        binding.btnUpdate.setOnClickListener {

            openUpdateDialog(
                arguments?.getString("patientId").toString(),
                arguments?.getString("patientName").toString()
            )
        }

        // Delete button.
        binding.btnDelete.setOnClickListener {

            deleteRecord(
                arguments?.getString("patientId").toString()
            )
        }
    }

    // Display values received from Fetch Fragment.
    private fun setValuesToViews() {

        binding.tvEmpId.text =
            arguments?.getString("patientId")

        binding.tvDrugName.text =
            arguments?.getString("patientName")

        binding.tvDrugDescription.text =
            arguments?.getString("patientDiscription")

        binding.tvSideEffect.text =
            arguments?.getString("patientSideEffect")

        binding.tvPrevention.text =
            arguments?.getString("patientPrevention")

        binding.tvTreatment.text =
            arguments?.getString("patientTreatment")
    }

    // Delete selected patient.
    private fun deleteRecord(id: String) {

        val dbRef = FirebaseDatabase
            .getInstance()
            .getReference("Patients")
            .child(id)

        dbRef.removeValue()
            .addOnSuccessListener {

                Toast.makeText(
                    requireActivity(),
                    "Patient Deleted Successfully",
                    Toast.LENGTH_SHORT
                ).show()

                // Return to previous screen.
                findNavController().popBackStack()

            }
            .addOnFailureListener {

                Toast.makeText(
                    requireActivity(),
                    it.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    // Opens Update Dialog.
    private fun openUpdateDialog(
        patientId: String,
        patientName: String
    ) {

        val dialogBuilder = AlertDialog.Builder(requireActivity())

        val dialogView = layoutInflater.inflate(
            R.layout.update_dialog,
            null
        )

        dialogBuilder.setView(dialogView)

        val etDrugName =
            dialogView.findViewById<EditText>(R.id.etDrugName)

        val etDrugDescription =
            dialogView.findViewById<EditText>(R.id.etDrugDescription)

        val etSideEffect =
            dialogView.findViewById<EditText>(R.id.etSideEffect)

        val etPrevention =
            dialogView.findViewById<EditText>(R.id.etPrevention)

        val etTreatment =
            dialogView.findViewById<EditText>(R.id.etTreatment)

        val btnUpdateData =
            dialogView.findViewById<Button>(R.id.btnUpdateData)

        // Put previous values inside EditTexts.
        etDrugName.setText(arguments?.getString("patientName"))
        etDrugDescription.setText(arguments?.getString("patientDiscription"))
        etSideEffect.setText(arguments?.getString("patientSideEffect"))
        etPrevention.setText(arguments?.getString("patientPrevention"))
        etTreatment.setText(arguments?.getString("patientTreatment"))

        dialogBuilder.setTitle("Updating $patientName")

        val alertDialog = dialogBuilder.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener {

            updatePatientData(

                patientId,

                etDrugName.text.toString(),

                etDrugDescription.text.toString(),

                etSideEffect.text.toString(),

                etPrevention.text.toString(),

                etTreatment.text.toString()
            )

            Toast.makeText(
                requireActivity(),
                "Patient Updated Successfully",
                Toast.LENGTH_SHORT
            ).show()

            // Update UI without reopening fragment.
            binding.tvDrugName.text =
                etDrugName.text.toString()

            binding.tvDrugDescription.text =
                etDrugDescription.text.toString()

            binding.tvSideEffect.text =
                etSideEffect.text.toString()

            binding.tvPrevention.text =
                etPrevention.text.toString()

            binding.tvTreatment.text =
                etTreatment.text.toString()

            alertDialog.dismiss()
        }
    }

    // Replace old Firebase data with new data.
    private fun updatePatientData(

        id: String,

        name: String,

        description: String,

        sideEffect: String,

        prevention: String,

        treatment: String

    ) {

        val patientInfo = RealtimeDatabasePatientData(

            id,

            name,

            description,

            sideEffect,

            prevention,

            treatment
        )

        FirebaseDatabase
            .getInstance()
            .getReference("Patients")
            .child(id)
            .setValue(patientInfo)
    }
}