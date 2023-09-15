package com.datnt.alarmmanager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AlarmReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        // Start Service để hiển thị Notification và phát chuông
        val serviceIntent = Intent(context, AlarmService::class.java)
        context?.startService(serviceIntent)
    }
}