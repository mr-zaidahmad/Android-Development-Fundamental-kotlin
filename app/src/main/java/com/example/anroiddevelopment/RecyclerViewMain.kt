package com.example.anroiddevelopment

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recycler_view_main)  //calling xml file here

        val namess=arrayOf<Any>(
            getString(R.string.zaid),
            getString(R.string.hamza),
            getString(R.string.shahid),
            getString(R.string.abbas),
            getString(R.string.abdullah),
            getString(R.string.ibrar),
            getString(R.string.hamza),
            getString(R.string.yasin),
            getString(R.string.nehal)
            ,1,3,5,7,4,7,8.9,1.0,2.0,3.0)
        val calling=findViewById<RecyclerView>(R.id.Listkotlin)  //here we call the recylclerView from activity_recycler_view_main.xml
// through RecyclerViewMain id.

        calling.layoutManager= LinearLayoutManager(this)  //here we tell them that i want my item to be displayed in linear from that
//that is list form like youtube.

  //        calling.layoutManager= GridLayoutManager(this,3) //can also use grid layout which will display item in how much u want like
  //                                                     //here i tell them to display 3 item in one row. i'm commenting this
                                                          //now cuz i want to use liner form.


        calling.adapter= adapter(namess)  //and lastly This line connects your adapter to the RecyclerView
// so it knows what data to show and how to show it.
    }

}







