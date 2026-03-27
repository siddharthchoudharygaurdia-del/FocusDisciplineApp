package com.example.focusdisciplineapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // Handle the alarm event here
        Log.d("AlarmReceiver", "Alarm received!");
        // You can add further actions to perform when the alarm triggers.
    }
}