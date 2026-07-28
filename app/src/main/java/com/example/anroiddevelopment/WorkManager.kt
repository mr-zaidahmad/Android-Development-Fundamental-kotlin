package com.example.anroiddevelopment

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit


/*

* WorkManager is an Android Jetpack library used to run reliable
* background tasks that should finish even if the app is closed
* or the device restarts.
*
* Use it for tasks like uploading files, syncing data,
* downloading updates, or backing up information.
*
* Difference:
* Service = Runs immediately for continuous tasks like
* playing music or location tracking.
*
* WorkManager = Runs background tasks that can wait but
* must eventually complete.
*/

class WorkManager : AppCompatActivity() {

    // Gets a single WorkManager object for this app.
    // We use this object to schedule (enqueue) background tasks.
    private val workManager = WorkManager.getInstance(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_work_manager)

        // Start our background work.
        dowork()
    }

    private fun dowork() {

        // Create a one-time work request.
        // This tells Android to run the DemoWorkManager class only once.
        val request = OneTimeWorkRequest.Builder(DemoWorkManager::class.java)

            // Add conditions (constraints).
            // Here we tell Android to start this work only when
            // the device has an internet connection.
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            )

            // If the work fails, Android will wait 10 seconds
            // before trying again using Linear Backoff.
            .setBackoffCriteria(
                BackoffPolicy.LINEAR,
                10,
                TimeUnit.SECONDS
            )

            // Build the work request.
            .build()

        // Give the work request to Android.
        // Android will run it whenever the conditions are satisfied.
        workManager.enqueue(request)

        // Observe the current state of the worker.
        // This lets us know whether the worker is:
        // ENQUEUED -> Waiting
        // RUNNING -> Currently executing
        // SUCCEEDED -> Finished successfully
        // FAILED -> Finished with an error
        workManager.getWorkInfoByIdLiveData(request.id).observe(this) {
            if (it != null) {
                printStatus(it.state.name)
            }
        }
    }

    // Prints the current worker state in Logcat.
    fun printStatus(name: String) {
        Log.d("WorkerManager", name)
    }
}

