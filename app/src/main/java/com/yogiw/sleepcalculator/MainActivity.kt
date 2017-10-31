package com.yogiw.sleepcalculator

import android.app.TimePickerDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    var time: TimeClass = TimeClass(0, 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var listTime: ArrayList<TimeClass> = ArrayList()
        rbSleep.isChecked = true

        val currentTime: Calendar = Calendar.getInstance()
        val hour = currentTime.get(Calendar.HOUR_OF_DAY)
        val minute = currentTime.get(Calendar.MINUTE)
        time.Hour = hour
        time.Minute = minute

        tvTime.text = "${String.format("%02d", hour)}:${String.format("%02d", minute)}"
        listTime = calculateWakeUp(time)
        var adapter = TimeAdapter(applicationContext, listTime)
        rvTime.adapter = adapter
        rvTime.layoutManager = LinearLayoutManager(this)
        adapter.notifyItemInserted(listTime.size)

        rbSleep.setOnClickListener { v ->
            listTime.clear()
            if (rbSleep.isChecked == true) {
                listTime.addAll(calculateWakeUp(time))
            } else {
                listTime.addAll(calculateSleep(time))
            }
            adapter.notifyDataSetChanged()
        }
        rbWakeup.setOnClickListener { v ->
            listTime.clear()
            if (rbSleep.isChecked == true) {
                listTime.addAll(calculateWakeUp(time))
            } else {
                listTime.addAll(calculateSleep(time))
            }
            adapter.notifyDataSetChanged()
        }

        tvTime.setOnClickListener({ v ->
            val mTimePicker: TimePickerDialog

            mTimePicker = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { timePicker, selectedHour, selectedMinute ->
                time.Hour = selectedHour
                time.Minute = selectedMinute
                listTime.clear()

                if (rbSleep.isChecked == true) {
                    listTime.addAll(calculateWakeUp(time))
                    Log.i("isChecked", "" + true)
                } else {
                    listTime.addAll(calculateSleep(time))
                    Log.i("isChecked", "" + false)

                }
                adapter.notifyDataSetChanged()
                tvTime.text = "${String.format("%02d", selectedHour)}:${String.format("%02d", selectedMinute)}"
            }, hour, minute, true)//Yes 24 hour time
            mTimePicker.setTitle("Select Time")
            mTimePicker.show()
        })


    }

    fun calculateWakeUp(time: TimeClass): ArrayList<TimeClass> {
        var listSleepTime: ArrayList<TimeClass> = ArrayList()

        var hour: Int
        for (i in 1..10) {
            var minute = time.Hour * 60 + time.Minute
            minute = minute + (90 * i)
            Log.i("info", "" + minute)
            hour = minute / 60

            if (hour >= 24) {
                hour = hour - 24
            }

            Log.i("info", "" + i + hour)

            listSleepTime.add(TimeClass(hour, minute % 60, 90 * i / 60, 90 * i % 60, i, true))
        }

        return listSleepTime
    }

    fun calculateSleep(time: TimeClass): ArrayList<TimeClass> {
        var listSleepTime: ArrayList<TimeClass> = ArrayList()

        var hour: Int
        for (i in 1..10) {
            var minute = time.Hour * 60 + time.Minute
            minute = minute - (90 * i)
            Log.i("info", "" + minute)
            hour = minute / 60

            if (hour < 0) {
                hour = hour + 24
            }
            var mMinute: Int
            if (minute % 60 < 0) {
                mMinute = -1 * minute % 60
            } else {
                mMinute = minute % 60
            }

            Log.i("info", "" + i + hour)

            listSleepTime.add(TimeClass(hour, mMinute, 90 * i / 60, 90 * i % 60, i, false))
        }

        return listSleepTime
    }


}
