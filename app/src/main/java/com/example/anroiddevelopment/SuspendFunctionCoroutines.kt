package com.example.anroiddevelopment

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield
import okhttp3.Dispatcher



// A suspend function is a function that can pause its execution
// without blocking the thread, and then continue from where it stopped.
//it does that bcz some execution takes time so what jump from those execution to another coroutine
//so that the execution doesn't stop and give us output quick
//here is this code u will get the idea of how it works.

class SuspendFunctionCoroutines : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_suspended_function_coroutines)

        lifecycleScope.launch(Dispatchers.Main){
            task1()
        }
        lifecycleScope.launch (Dispatchers.Main){
            task2()
        }

    }

    //here we create two suspend function task1 and task2 they will show how the suspend function work
    // we use the yield() function in our task so that we tell the difference of how the suspend works
    //basically the yield is like I'm willing to pause for a moment. If another coroutine is waiting
    // to run, let it run first. Then come back to me later.
    //so using yield is the best example of showing how it works.
    private suspend fun task1() {
        Log.d("Suspend Function","first of all this task 1 log 1 will run")  //1:first this will run cuz it the first execution.
        yield()  //once the execution comes here it will look for another coroutine and it will jump there and execute the that code.
            //so it will jump to the next suspend funtion.which is task2 function.

        Log.d("Suspend Function","Third of all this task 1 log 2 will run") //3:Now it will run this code.Once the execution
        // of this function is done. then it will go to another function beneath it which is task2 function.

    }

    private suspend fun task2() {
        Log.d("Suspend Function","Second of all this task 2 log 1 will run") //2: Now it will jump here cuz of the yield function.
                                                                                        //and execute this line.
        yield()  //Now again it faces the Yield() function so it will again look for another coroutine which is suspend task1 function so it will
                //jump back to task1 function.
        Log.d("Suspend Function","Fourth of all this task 2 log 2 will run")//4:In the end this line will run cux it the last line.
         //once this is done then it will execute task1 yield() and the n task2 yield().
    }
}