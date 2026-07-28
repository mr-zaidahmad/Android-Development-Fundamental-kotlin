package com.example.anroiddevelopment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anroiddevelopment.databinding.FragmentFirebaseRealtimeDatabaseFetchDataBinding
import com.google.firebase.database.*

class FirebaseRealtimeDatabaseFetchData : Fragment() {

    lateinit var binding: FragmentFirebaseRealtimeDatabaseFetchDataBinding

    // Reference of Patients node
    private lateinit var dbReference: DatabaseReference

    // List that stores all fetched records
    lateinit var patientList: ArrayList<RealtimeDatabasePatientData>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFirebaseRealtimeDatabaseFetchDataBinding.inflate(
            layoutInflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.firebaserecyclerview.layoutManager =
            LinearLayoutManager(requireActivity())

        binding.firebaserecyclerview.setHasFixedSize(true)

        patientList = arrayListOf()

        GetEmployeesData()
    }

    private fun GetEmployeesData() {

        binding.firebaserecyclerview.visibility = View.GONE

        dbReference = FirebaseDatabase.getInstance().getReference("Patients")

        dbReference.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                patientList.clear()

                if (snapshot.exists()) {

                    for (patientsnap in snapshot.children) {

                        val patientData =
                            patientsnap.getValue(RealtimeDatabasePatientData::class.java)

                        // IMPORTANT:
                        // Firebase creates a unique key for every record.
                        // We save that key because Update/Delete need it.
                        patientData?.patientId = patientsnap.key

                        patientList.add(patientData!!)
                    }

                    val adapter = FirebaseAdapter(patientList)

                    binding.firebaserecyclerview.adapter = adapter
                    binding.firebaserecyclerview.visibility = View.VISIBLE

                    adapter.setonitemclickListener(object :
                        FirebaseAdapter.onitemclickListener {

                        override fun onclick(position: Int) {

                            val bundle = Bundle()

                            // Pass every value to Details Fragment

                            bundle.putString(
                                "patientId",
                                patientList[position].patientId
                            )

                            bundle.putString(
                                "patientName",
                                patientList[position].patientName
                            )

                            bundle.putString(
                                "patientDiscription",
                                patientList[position].patientDiscription
                            )

                            bundle.putString(
                                "patientSideEffect",
                                patientList[position].patientSideEffect
                            )

                            bundle.putString(
                                "patientPrevention",
                                patientList[position].patientPrevention
                            )

                            bundle.putString(
                                "patientTreatment",
                                patientList[position].patientTreatment
                            )

                            // Navigate to Details Fragment and send Bundle
                            findNavController().navigate(
                                R.id.action_firebaseRealtimeDatabaseFetchData_to_firebaseRealtimeDatabaseDetails,
                                bundle
                            )
                        }
                    })
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}