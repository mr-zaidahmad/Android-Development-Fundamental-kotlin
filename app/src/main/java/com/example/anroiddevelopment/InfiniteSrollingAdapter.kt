package com.example.anroiddevelopment

import android.R.attr.layout
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView

class Aadapter(val itemss : MutableList<String>): RecyclerView.Adapter<Aadapter.VviewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VviewHolder {
        val layout : LayoutInflater= LayoutInflater.from(parent.context)
        val view : View=layout.inflate(R.layout.infinite_scrolling_items,parent,false)
                return VviewHolder(view)
    }

    override fun onBindViewHolder(holder: VviewHolder, position: Int) {
        holder.textView.text=itemss[position]
    }

    override fun getItemCount(): Int {
        return itemss.size
    }


    class VviewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textView=itemView.findViewById<TextView>(R.id.Text)
    }
}