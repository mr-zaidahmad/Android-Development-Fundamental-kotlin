package com.example.anroiddevelopment

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.anroiddevelopment.databinding.ActivitySpinnerBinding

class Spinner : AppCompatActivity() {
     lateinit var binding: ActivitySpinnerBinding
     var isFirstcountrySelection=true
    var isfirstcityselected=true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivitySpinnerBinding.inflate(layoutInflater)
          setContentView(binding.root)

      val spinnerCountry=binding.SpinnerCountry


        val adapter= ArrayAdapter.createFromResource(
            this,
            R.array.Countries,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )
        spinnerCountry.adapter=adapter


        val selectCountry=spinnerCountry.selectedItem.toString()

        spinnerCountry.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if(isFirstcountrySelection){
                    isFirstcountrySelection=false
                    return
                }
                val country = parent?.getItemAtPosition(position).toString()
                Toast.makeText(this@Spinner, "$country Selected", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }




        val spinnerCity=binding.spinnerCity

        val adapterr= ArrayAdapter.createFromResource(
            this,
            R.array.City,
            android.R.layout.simple_spinner_item
        )

        adapterr.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )
        spinnerCity.adapter=adapterr

        spinnerCity.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (isfirstcityselected){
                    isfirstcityselected=false
                    return
                }
                val city=parent?.getItemAtPosition(position).toString()
                Toast.makeText(this@Spinner,"$city Selected", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }


        }



    }
}