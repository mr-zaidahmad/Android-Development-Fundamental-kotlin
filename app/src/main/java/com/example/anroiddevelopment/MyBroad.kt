package com.example.anroiddevelopment

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast


class MyBroad : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

     when(intent?.action){
         Intent.ACTION_POWER_CONNECTED->{
             Toast.makeText(context, "Charger Connected", Toast.LENGTH_SHORT).show()

         }
         Intent.ACTION_POWER_DISCONNECTED->{
             Toast.makeText(context, "Charger DisConnected", Toast.LENGTH_SHORT).show()

         }
     }
    }

}