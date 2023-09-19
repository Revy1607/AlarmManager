package com.datnt.alarmmanager

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.app.PendingIntent.FLAG_MUTABLE
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TimePicker
import android.widget.Toast
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private lateinit var timePicker: TimePicker
    private var repeatCheckbox: CheckBox? = null
    private var setAlarmButton: Button? = null

    private var alarmManager: AlarmManager? = null
    private var pendingIntent: PendingIntent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timePicker = findViewById(R.id.timePicker)
        repeatCheckbox = findViewById(R.id.repeatCheckbox)
        setAlarmButton = findViewById(R.id.setAlarmButton)


        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java)
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)


        setAlarmButton?.setOnClickListener {
            setAlarm()
            Toast.makeText(this, "Đặt báo thức thành công lúc ${timePicker.hour}:${timePicker.minute}", Toast.LENGTH_LONG).show()
        }

    }

    private fun setAlarm(){
        //Tạo calendat và đặt giờ
        val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, timePicker.hour)
            calendar.set(Calendar.MINUTE, timePicker.minute)
            calendar.set(Calendar.SECOND, 0)

        //Kiểm tra Checkbox để quyết định liệu báo thức có lặp lại hay không
        val repeat = repeatCheckbox?.isChecked


        //Sử dụng AlarmManager để đặt báo thức
        if(repeat == true){
            alarmManager?.setRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                pendingIntent
            )
            Toast.makeText(this, "Báo thức được lặp lại", Toast.LENGTH_SHORT).show()
        }else {
            alarmManager?.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
        }
    }
}