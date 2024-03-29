package com.example.brodcast

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Button
import androidx.activity.ComponentActivity


class MainActivity : ComponentActivity() {

    private lateinit var receiver: Receiver
    private lateinit var alarmManager: AlarmManager

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Register the receiver
        receiver = Receiver()
        val filter = IntentFilter("ACTION_ALARM")
        registerReceiver(receiver, filter)

        // Initialize AlarmManager
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager



        // Find buttons by their IDs
        val btnBroadcastViaIntent = findViewById<Button>(R.id.btnBroadcastViaIntent)
        val btnDynamicBroadcastRecViaAlarm = findViewById<Button>(R.id.btnDynamicBroadcastRecViaAlarm)
        val btnXMLStaticBroadcastRecViaAlarm = findViewById<Button>(R.id.btnXMLStaticBroadcastRecViaAlarm)
        // Set click listeners for each button
        btnBroadcastViaIntent.setOnClickListener { v -> onClick(v) }
        btnDynamicBroadcastRecViaAlarm.setOnClickListener { v -> onClick(v) }
        btnXMLStaticBroadcastRecViaAlarm.setOnClickListener { v -> onClick(v) }
    }

    private fun onClick(v: View) {
        when (v.id) {
            R.id.btnBroadcastViaIntent -> {
                val intent = Intent("ACTION_ALARM").apply {
                    putExtra("param", "Bonjour via Intent immediatly ")
                }
                sendBroadcast(intent)
            }
            R.id.btnDynamicBroadcastRecViaAlarm -> {
                val intent = Intent("ACTION_ALARM").apply {
                    putExtra("param", "Bonjour via Alarm after 3sec ")
                }
                val pendingIntent = PendingIntent.getBroadcast(
                    applicationContext,
                    0,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )

                val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
                val triggerTime = SystemClock.elapsedRealtime() + 3000
                alarmManager.set(
                    AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    triggerTime,
                    pendingIntent
                )
            }
             R.id.btnXMLStaticBroadcastRecViaAlarm -> {
                    val intent = Intent("ACTION_ALARM_FILTRED").apply {
                        putExtra("param", "Bonjour via Alarm (receiver in xml)")
                    }

                    val pendingIntent = PendingIntent.getBroadcast(
                        applicationContext,
                        0,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                    )

                    val triggerTime = System.currentTimeMillis() + 3000
                    alarmManager.set(
                        AlarmManager.RTC_WAKEUP,
                    triggerTime,
                    pendingIntent
                )
            }


        }
    }

}
