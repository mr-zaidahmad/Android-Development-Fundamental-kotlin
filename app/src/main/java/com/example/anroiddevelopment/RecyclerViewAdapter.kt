package com.example.anroiddevelopment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//recyclerView is based on two things 1:Adapter 2:Viewholder
//but for that u have to make one class that is for Adapter and make another nested class for viewholder
//we made it nested cuz view holder and adpater are link to each other.

//first let make adapter class. and then call RecyclerView through by : and then in recycler view there are
//two option one is Adapter and the other is viewholder so we take Adapter cuz we are making Adapter class rn.
//once that is done then u have to also call it's nested class of viewholder cuz we alerady told that they are linked
//to each other.
class adapter(val data : Array<Any>): RecyclerView.Adapter<adapter.MYviewholder>(){

    //after that Adapter have three functions which is necessary those are
    // 1:to create view holder and inflates the xml
    //2:to bindviewholder work is to position those item in each box.
    //get itemcount work is to tell recycler view how many item do u have in the list.

    //1:oncreateviewholder.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MYviewholder {
        val layout: LayoutInflater= LayoutInflater.from(parent.context)  // this line job is to is to convert an XML layout file into
        // an actual View object that can be displayed on screen.
        val vieww: View=layout.inflate(R.layout.recyclerview_item_list,parent,false)  //This line actually converts the XML file
        // into a real View using the LayoutInflater we created on the previous line
        return MYviewholder(vieww)  //and last this line call the nested class MYviewholder and pass the vieww variable from above line.
    }

    override fun onBindViewHolder(holder: MYviewholder,position: Int) {
        holder.textview.text=data[position].toString()   //This line fills the TextView of each list item with the correct data.
        //here we use .toString() fun cuz our array have <Any> which means we have strings,Int and double
        //so this word convert all of them to string.
    }

    override fun getItemCount(): Int {
        return data.size  //This line returns the total number of items in your data array to the RecyclerView.
    }


    class MYviewholder(itemView: View): RecyclerView.ViewHolder(itemView){
        //These two lines connect the views inside a single list item XML to
        // variables so we can use them later in onBindViewHolder.as u can see
        //we did it with Textview variable in OnBindviewholder.
        val imgicon=itemView.findViewById<ImageView>(R.id.imgIcon)
        val textview=itemView.findViewById<TextView>(R.id.textitle)
    }
    //this is how recylerView is done..
}
