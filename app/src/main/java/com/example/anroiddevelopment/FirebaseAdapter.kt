package com.example.anroiddevelopment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FirebaseAdapter(
    private val data: ArrayList<RealtimeDatabasePatientData>
) : RecyclerView.Adapter<FirebaseAdapter.ViewHolder>() {

    // Interface for RecyclerView item click.
    private lateinit var mListener: onitemclickListener

    interface onitemclickListener {
        fun onclick(position: Int)
    }

    fun setonitemclickListener(clickListener: onitemclickListener) {
        mListener = clickListener
    }

    // Called whenever RecyclerView needs a new row.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.firebase_item_list, parent, false)

        return ViewHolder(itemView, mListener)
    }

    // Called to display data inside each row.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentPatient = data[position]

        // Showing patient(drug) name inside RecyclerView.
        holder.patientName.text = currentPatient.patientName
    }

    // Total number of items.
    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(
        itemView: View,
        clickListener: onitemclickListener
    ) : RecyclerView.ViewHolder(itemView) {

        // TextView inside firebase_item_list.xml
        val patientName: TextView =
            itemView.findViewById(R.id.RecyclyviewText)

        init {

            // Called when user clicks any RecyclerView item.
            itemView.setOnClickListener {

                // bindingAdapterPosition gives the clicked item's position.
                // We send this position back to the Fragment.
                clickListener.onclick(bindingAdapterPosition)
            }
        }
    }
}