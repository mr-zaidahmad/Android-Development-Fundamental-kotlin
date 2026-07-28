package com.example.anroiddevelopment

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

/*
 * Coroutine Builders
 *
 * There are two commonly used coroutine builders:
 *
 * 1. launch
 *    - Used when you just want to perform a task.
 *    - It does not return any useful result.
 *    - It returns a Job object.
 *
 * 2. async
 *    - Used when you want to perform a task and get a result back.
 *    - It returns a Deferred object.
 *    - We use await() to get the returned value.
 *
 * In this example:
 * We are pretending to fetch Facebook and Instagram followers.
 * Both tasks start together using async, so they run concurrently.
 * Then await() waits for both results before printing them.
 */

class CoroutinesBuilders : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_coroutines_builders)

        // launch starts our first coroutine.
        // We use launch because we only want to start the work.
        // We are not expecting launch itself to return any value.
        CoroutineScope(Dispatchers.Main).launch {

            // Calling our function that will fetch followers.
            Printfollowers()

        }
    }

    private fun Printfollowers() {

        // Creating another coroutine on the IO Dispatcher.
        // IO Dispatcher is used for tasks like network calls,
        // database operations, file reading/writing, etc.
        CoroutineScope(Dispatchers.IO).launch {

            // async starts another coroutine and returns a Deferred object.
            // Unlike launch, async is used when we expect a result back.

            val fb = async {

                // Calling a suspend function which returns Facebook followers.
                getfbfollowers()

            }

            val insta = async {

                // Calling another suspend function which returns Instagram followers.
                getinstafollowers()

            }

            // await() waits until the async coroutine finishes
            // and then gives us the returned value.

            Log.d(
                "CoroutinesBuilders",
                "Facebook Followers = ${fb.await()}\nInstagram Followers = ${insta.await()}"
            )

        }
    }

    // Pretending to fetch Facebook followers from a server.
    // delay() simulates a network request taking 1 second.
    private suspend fun getfbfollowers(): Int {

        delay(1000.milliseconds)

        return 180
    }

    // Pretending to fetch Instagram followers from a server.
    // Again, delay() simulates the network request.
    private suspend fun getinstafollowers(): Int {

        delay(1000.milliseconds)

        return 70
    }
}