package com.example.anroiddevelopment

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class DemoWorkManager(context: Context, params: WorkerParameters) : Worker(context, params) {

    // doWork() is the main function of every Worker.
    // Android automatically calls this function when it is time
    // to run the background task.
    override fun doWork(): Result {

        // Call our function that contains the actual background work.
        performWork()

        // Tell Android that the work completed successfully.
        // We can also return:
        // Result.failure() -> if the work failed.
        // Result.retry()   -> if we want Android to try again later.
        return Result.retry()
    }

    // This function contains the task we want to perform
    // in the background.
    fun performWork() {

        // Wait for 3 second.
        // This is only to simulate a long-running task.
        // In a real app, this could be uploading a file,
        // syncing data, downloading updates, etc.
        Thread.sleep(3000)

        // Print a message in Logcat after the work is finished.
        Log.d("WorkManager", "WorkManager Complete")
    }
}
