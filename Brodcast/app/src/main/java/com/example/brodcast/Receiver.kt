package com.example.brodcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class Receiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == "ACTION_ALARM") {
            val param = intent.getStringExtra("param")
            Toast.makeText(context, "Received Broadcast: $param", Toast.LENGTH_SHORT).show()
        }
    }
}