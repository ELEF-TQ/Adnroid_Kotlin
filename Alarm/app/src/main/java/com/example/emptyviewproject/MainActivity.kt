package com.example.emptyviewproject

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var timePicker: TimePicker
    private lateinit var btnTimer: Button
    private var hour: Int = 0
    private var minute: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timePicker = findViewById(R.id.timePicker)
        btnTimer = findViewById(R.id.btnTimer)
        timePicker.setIs24HourView(true)
        timePicker.setOnTimeChangedListener { _, selectedHour, selectedMinute ->
            hour = selectedHour
            minute = selectedMinute
        }

        btnTimer.setOnClickListener {
            Toast.makeText(this, "Set Alarm $hour : $minute", Toast.LENGTH_SHORT).show()
            setTimer()
            notification()
        }
    }

    private fun notification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Alarm Reminders"
            val description = "This channel is used for alarm notifications."
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("Notify", name, importance)
            channel.description = description
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    @SuppressLint("ShortAlarm")
    private fun setTimer() {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val calAlarm = Calendar.getInstance()
        val calNow = Calendar.getInstance()
        calNow.time = Date()
        calAlarm.time = calNow.time
        calAlarm.set(Calendar.HOUR_OF_DAY, hour)
        calAlarm.set(Calendar.MINUTE, minute)
        calAlarm.set(Calendar.SECOND, 0)
        if (calAlarm.before(calNow)) {
            Log.d("AlarmDebug", "Chosen time is in the past, setting for tomorrow")
            calAlarm.add(Calendar.DATE, 1)
        } else {
            Log.d("AlarmDebug", "Chosen time is in the future")
        }
        val intent = Intent(this, MyBroadcastReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        val offset = 5000
        calAlarm.timeInMillis += offset
        alarmManager.set(AlarmManager.RTC_WAKEUP, calAlarm.timeInMillis, pendingIntent)
        Log.d("AlarmDebug", "Alarm set for: ${calAlarm.time}")
    }
}
