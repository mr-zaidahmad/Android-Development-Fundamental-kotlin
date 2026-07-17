package com.example.anroiddevelopment

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.widget.TextView
import kotlin.concurrent.fixedRateTimer

class MainAdapter(
    private val list: List<String>,
    private val onItemClick: (Int) -> Unit
) : RecyclerView.Adapter<MainAdapter.MainViewHolder>(){


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainViewHolder {
        val view : View= LayoutInflater.from(parent.context)
            .inflate(R.layout.main_item,parent,false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: MainViewHolder,
        position: Int
    ) {
       holder.textview.text=list[position]
        holder.itemView.setOnClickListener {
            onItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


    class MainViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
   val textview= itemView.findViewById<TextView>(R.id.txtTopic)
    }
}