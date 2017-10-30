package com.yogiw.sleepcalculator

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TimePicker
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        tvTime.setOnClickListener( {v ->
            val currentTime: Calendar = Calendar.getInstance()
            val hour = currentTime.get(Calendar.HOUR_OF_DAY)
            val minute = currentTime.get(Calendar.MINUTE)
            val mTimePicker: TimePickerDialog

            var time: TimeClass = TimeClass(0,0)
            mTimePicker = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { timePicker, selectedHour, selectedMinute ->
                        time = TimeClass(selectedHour, selectedMinute)
                        var adapter = TimeAdapter(applicationContext, calculate(time))
                        rvTime.adapter = adapter
                    }, hour, minute, true)//Yes 24 hour time
            mTimePicker.setTitle("Select Time")
            mTimePicker.show()
        } )


    }

    fun calculate(time: TimeClass): ArrayList<TimeClass>{
        var listSleepTime: ArrayList<TimeClass> = ArrayList()

        var minute: Int
        minute = time.Hour * 60 + time.Minute

        minute = minute + 90

        listSleepTime.add(TimeClass(minute / 60, minute % 60))


        return listSleepTime
    }
}
