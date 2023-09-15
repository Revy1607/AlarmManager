package com.datnt.alarmmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TimePicker

class MainActivity : AppCompatActivity() {
    private var timePicker: TimePicker? = null
    private var repeatCheckbox: CheckBox? = null
    private var setAlarmButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timePicker = findViewById(R.id.timePicker)
        repeatCheckbox = findViewById(R.id.repeatCheckbox)
        setAlarmButton = findViewById(R.id.setAlarmButton)

        
    }
}