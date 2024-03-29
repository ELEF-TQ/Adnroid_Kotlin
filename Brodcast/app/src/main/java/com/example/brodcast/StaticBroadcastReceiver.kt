package com.example.brodcast
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class StaticBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val param = intent.getStringExtra("param")
        Log.d("StaticBroadcastReceiver", "Received Broadcast: $param")
        Toast.makeText(context, "Received Broadcast: $param", Toast.LENGTH_SHORT).show()
    }

}
