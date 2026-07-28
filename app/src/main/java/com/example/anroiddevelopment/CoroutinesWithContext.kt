package com.example.anroiddevelopment

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.time.Duration.Companion.seconds

/*
 * withContext
 *
 * withContext() is used to switch from one Dispatcher (thread)
 * to another and wait until that work is completed.
 *
 * Unlike launch(), withContext() does NOT create a new coroutine.
 * It simply changes the current coroutine's thread temporarily.
 *
 * Once the work inside withContext() is finished,
 * execution comes back to the previous Dispatcher automatically.
 *
 * We mostly use withContext(Dispatchers.IO) for:
 * - Network requests
 * - Database operations
 * - File reading/writing
 * or any long-running background task.
 */

class CoroutinesWithContext : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_coroutines_with_context)

        // Starting a coroutine on the Main Thread.
        // From here we call our suspend function.
        lifecycleScope.launch(Dispatchers.Main) {

            executeTask()

        }
    }

    private suspend fun executeTask() {

        /*
         * Example 1 : Using launch()
         *
         * launch() creates another coroutine.
         * The parent coroutine DOES NOT wait for it to finish.
         *
         * Output:
         * Before
         * After
         * (2 Seconds Later)
         * Inside
         */

//        Log.d("ZAID_TEST", "Before")
//
//        GlobalScope.launch {
//
//            delay(2.seconds)
//
//            Log.d("ZAID_TEST", "Inside")
//
//        }
//
//        Log.d("ZAID_TEST", "After")


        //-------------------------------------------------------------


        /*
         * Example 2 : Using withContext()
         *
         * withContext() does NOT create another coroutine.
         * It simply changes the Dispatcher from Main to IO.
         *
         * The current coroutine waits until the code inside
         * withContext() is finished.
         *
         * After the work is completed,
         * execution automatically returns to the Main Thread.
         *
         * Output:
         * Before
         * (Wait 2 Seconds)
         * Inside
         * After
         */

        Log.d("ZAID_TEST", "Before")

        // Switching from Main Thread to IO Thread.
        withContext(Dispatchers.IO) {

            // Simulating a long-running task.
            delay(2.seconds)

            Log.d("ZAID_TEST", "Inside")

        }

        // Once withContext() is finished,
        // execution comes back to the Main Thread.
        Log.d("ZAID_TEST", "After")
    }
}