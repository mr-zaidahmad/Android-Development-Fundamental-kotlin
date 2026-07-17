package com.example.anroiddevelopment

import android.app.Service
import android.content.Intent
import android.os.Bundle
import android.os.IBinder
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import kotlin.concurrent.thread

class LoggerServices : Service(){
    private var IsRunning=true

    override fun onCreate() {
        super.onCreate()
       Log.d(getString(R.string.zaid), getString(R.string.oncreate_called))
        //Toast.makeText(this,"Oncreate Called", Toast.LENGTH_SHORT).show()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(getString(R.string.zaid), getString(R.string.onstartcommand_called))
       // Toast.makeText(this,"OnstartCommand Called", Toast.LENGTH_SHORT).show()

        //if u want to see how the  task is working open logcat and search Zaid u
        //will see that the logging message is continuously running in the background
        //even if we go back but when we close the app it get stop
        //this is how music,file transfer works even if we close the app.

        thread{
            while (IsRunning){
                Log.d(getString(R.string.zaid), getString(R.string.logging_messege))
                Thread.sleep(1000)
            }
        }
        return super.onStartCommand(intent, flags, startId)

    }
    override fun onDestroy() {
        super.onDestroy()
        IsRunning =false
        Log.d(getString(R.string.zaid), getString(R.string.ondestroy_called))
      //  Toast.makeText(this,"OnDestroy Called", Toast.LENGTH_SHORT).show()

    }
    override fun onBind(intent: Intent?): IBinder? {
       return null
    }


}