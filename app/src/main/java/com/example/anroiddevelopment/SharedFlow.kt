package com.example.anroiddevelopment

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.anroiddevelopment.databinding.ActivitySharedFlowBinding
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch


//SharedFlow
//
//A SharedFlow is used for events.
//
//Unlike StateFlow, it doesn't hold a current value by default.
//
//Example:
//
//User clicks a button.
//
//Button Clicked
//
//After it's handled...
//
//It's gone.
//
//You don't need to remember it forever.




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





class SharedFlow : AppCompatActivity() {
    private lateinit var binding: ActivitySharedFlowBinding
    private var event= MutableSharedFlow<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding= ActivitySharedFlowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            event.collect {
                Log.d("HI_TEST",it)
            }
        }

        binding.sharedFloww.setOnClickListener {
            lifecycleScope.launch {
            event.emit("Login Successfull")
            event.emit("Welcome Zaid")
            event.emit("Navigate to Home Screen")
        }
    }
}
}