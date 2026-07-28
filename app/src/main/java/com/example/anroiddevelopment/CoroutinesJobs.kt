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
import kotlin.time.Duration.Companion.milliseconds

/*
 * Coroutine Job
 *
 * Every coroutine has a Job associated with it.
 * A Job represents the lifecycle of a coroutine.
 *
 * Using a Job we can:
 * 1. Wait for a coroutine to finish using join().
 * 2. Cancel a coroutine.
 * 3. Check whether a coroutine is active or completed.
 *
 * In this example:
 * - We create a Parent Coroutine.
 * - Inside it we create a Child Coroutine.
 * - The parent waits for 3 seconds.
 * - The child waits for 5 seconds.
 * - After the parent finishes, join() waits until the entire parent coroutine
 *   (including its child coroutine) is completed.
 */

class CoroutinesJobs : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_coroutines_jobs)

        // Starting our first coroutine on the Main Thread.
        // This coroutine simply calls the execute() function.
        lifecycleScope.launch(Dispatchers.Main) {

            execute()

        }
    }

    private suspend fun execute() {

        // Creating a Parent Coroutine.
        // launch() returns a Job object.
        // We store it inside parentjob so we can control it later.
        val parentjob = lifecycleScope.launch(Dispatchers.Main) {

            Log.d("ZAID_TEST", "Parent started")

            // Creating a Child Coroutine inside the Parent Coroutine.
            // Since this coroutine is inside the parent,
            // it becomes the child of the parent coroutine.
            val childjob = launch {

                Log.d("ZAID_TEST", "Child started")

                // Simulating some long-running work.
                delay(5000.milliseconds)

                Log.d("ZAID_TEST", "Child Ended")

            }

            // Parent coroutine waits for 3 seconds.
            delay(3000.milliseconds)

            Log.d("ZAID_TEST", "Parent Ended")

        }

        // join() waits until the Parent Job is completely finished.
        // Since the child belongs to the parent,
        // join() also waits for the child coroutine to finish.
        parentjob.join()

        // This line executes only after the Parent
        // and its Child coroutine have both completed.
        Log.d("ZAID_TEST", "Parent Completed")
    }
}