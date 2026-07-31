package com.example.anroiddevelopment

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.anroiddevelopment.databinding.ActivityFlowBinding
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch


//What is Flow?
//
//A Flow is a Kotlin feature that emits a stream of values over time.
//
//Instead of returning just one value, it can return many values.
//
//Think of it like this:
//
//Normal Function
//
//Returns one value.
//
//fun getNumber(): Int {
//    return 10
//}
//
//Output:
//
//10
//
//Finished.
//
//Flow
//
//Can return many values.
//
//10
//20
//30
//40
//50
//
//One after another.


class Flow : AppCompatActivity() {

    private lateinit var binding: ActivityFlowBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityFlowBinding.inflate(layoutInflater)
        setContentView(binding.root)
        numberFlow()

        binding.Floww.setOnClickListener {

            lifecycleScope.launch {
                numberFlow().collect {
                    Log.d("HI_TEST", it.toString())
                }
            }
        }
    }
    fun numberFlow()=flow{

        emit(1)
        emit(2)
        emit(3)
        emit(4)
        emit(5)
        emit(6)
        emit(7)
        emit(8)
        emit(9)
    }
        .filter { it > 1 }
        .map { it * 100 }
        .take(2)
    //Flow Operators
    //Just like Kotlin collections have functions like:
    //map()
    //filter()
    //take()
    //Flow has the same operators.
    //They let you modify the data before it reaches collect().
    //1. map()
    //map() changes each emitted value.
    //2. filter()
    //filter() removes values that don't match a condition.
    //3. take()
    //Receives only the first N values.

}