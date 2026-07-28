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
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.seconds

/*
 * runBlocking
 *
 * runBlocking is a coroutine builder that blocks the current thread
 * until all the code inside it has finished executing.
 *
 * Unlike launch(), runBlocking waits for every coroutine inside it
 * before allowing the program to continue.
 *
 * It is mainly used for:
 * - Learning coroutines
 * - Testing
 * - Writing main() functions
 *
 * It should NOT be used in Android UI code because it blocks
 * the Main Thread and can freeze the app.
 */

class CoroutinesRunBlocking : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_coroutines_run_blocking)

        // Starting a coroutine on the Main Thread.
        lifecycleScope.launch(Dispatchers.Main) {

            runblocking()

        }
    }

    private fun runblocking() {

        /*
         * Example 1 : Using launch()
         *
         * launch() starts another coroutine.
         * The current code DOES NOT wait for it to finish.
         *
         * Output:
         * Second
         * (Wait 1 Second)
         * First
         */

//        GlobalScope.launch {
//
//            delay(1.seconds)
//
//            Log.d("ZAID_TEST", "First")
//
//        }
//
//        Log.d("ZAID_TEST", "Second")


        //------------------------------------------------------------


        /*
         * Example 2 : Using runBlocking()
         *
         * runBlocking blocks the current thread until all
         * the coroutines inside it have completed.
         *
         * Even though launch() creates another coroutine,
         * runBlocking waits for that coroutine before exiting.
         *
         * Output:
         * Second
         * (Wait 1 Second)
         * First
         *
         * Notice:
         * The important difference is NOT the output.
         * The difference is that the current thread is blocked
         * until everything inside runBlocking finishes.
         */

        runBlocking {

            // Starting a child coroutine.
            launch {

                delay(1.seconds)

                Log.d("ZAID_TEST", "First")

            }

            // This line executes immediately.
            Log.d("ZAID_TEST", "Second")

        }

        // This line executes only after runBlocking has completely finished.
        Log.d("ZAID_TEST", "runBlocking Finished")

    }
}