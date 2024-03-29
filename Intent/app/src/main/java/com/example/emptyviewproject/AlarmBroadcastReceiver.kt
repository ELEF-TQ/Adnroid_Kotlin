import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class AlarmBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        // Log a message to ensure the receiver is triggered
        Log.d("AlarmBroadcastReceiver", "Alarm received!")

        // Display a toast message when the alarm triggers
        Toast.makeText(context.applicationContext, "Alarm received!", Toast.LENGTH_SHORT).show()
    }
}
