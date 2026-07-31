package com.example.anroiddevelopment

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.anroiddevelopment.databinding.ActivityStateFlowBinding
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch




//StateFlow
//
//A StateFlow holds one current value.
//
//Think of it as a variable that everyone can observe.
//
//Example:
//
//Score = 10
//
//Later:
//
//Score = 20
//
//Later:
//
//Score = 30
//
//It always remembers the latest value.



                       //DIFFERENCE BETWEEN STATEFLOW AND SHAREDFLOW
//StateFlow	SharedFlow
//Stores the latest value	Doesn't store the latest value by default
//Requires an initial value	No initial value required
//Updated using .value	Sends events using emit()
//Used for State	Used for Events
//StateFlow
//
//Used when your app has a current value that should always be available.
//
//Examples:
//
//Dark mode
//Username
//Loading state
//Score
//SharedFlow
//
//Used for one-time events that happen and then are finished.
//
//Examples:
//
//Toast
//Snackbar
//Navigation
//Dialog
//Memory Trick
//StateFlow → "What is the current value?"
//SharedFlow → "What just happened?"





class StateFlow : AppCompatActivity() {
    private var score= MutableStateFlow(0)
    private lateinit var binding: ActivityStateFlowBinding

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityStateFlowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
                score.collect {
                    Log.d("HI_TEST", "score=$it")
                }
            }
            binding.StateFloww.setOnClickListener {
                score.value = 2
                score.value = 3
                score.value = 4
            }
        }
    }

