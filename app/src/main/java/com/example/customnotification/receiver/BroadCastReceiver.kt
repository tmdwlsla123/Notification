package com.example.customnotification.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class BroadCastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        if(intent.getAction().equals(Intent.ACTION_SCREEN_ON)){
            var toast = Toast.makeText(context, "good", Toast.LENGTH_LONG)
            toast.show()
        }
        else{
            Toast.makeText(context, "good", Toast.LENGTH_LONG).show()
        }
    }
}
