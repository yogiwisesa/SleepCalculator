package com.yogiw.sleepcalculator.Activity

import android.app.TimePickerDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.yogiw.sleepcalculator.R
import com.yogiw.sleepcalculator.Adapter.TimeAdapter
import com.yogiw.sleepcalculator.Helper.SettingPref
import com.yogiw.sleepcalculator.Model.SettingList
import com.yogiw.sleepcalculator.Model.TimeClass
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList




class MainActivity : AppCompatActivity() {
    var am: Int = 0
    var cyclerdur: Int = 90
    var timetosleep: Int = 15

    var time: TimeClass = TimeClass(0, 0)
    var listTime: ArrayList<TimeClass> = ArrayList()
    var adapter: TimeAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // SHARED PREFERENCES
        val setting = SettingPref.load(this)
        if (setting == null) {
            SettingPref.clearAll(this@MainActivity)
        } else {
            am = setting.settingList[0].value
            cyclerdur = setting.settingList[1].value
            timetosleep = setting.settingList[2].value
            Log.i("SharePreference", "" + am + " " + cyclerdur + " " + timetosleep)
        }

        rbSleep.isChecked = true

        val currentTime: Calendar = Calendar.getInstance()
        val hour = currentTime.get(Calendar.HOUR_OF_DAY)
        val minute = currentTime.get(Calendar.MINUTE)
        time.Hour = hour
        time.Minute = minute

        tvTime.text = "${String.format("%02d", hour)}:${String.format("%02d", minute)}"
        adapter = TimeAdapter(this, listTime)
        listTime.addAll(calculateWakeUp(time))
        adapter!!.notifyDataSetChanged()
        rvTime.adapter = adapter
        rvTime.layoutManager = LinearLayoutManager(this)


        rbSleep.setOnClickListener { v ->
            listTime.clear()
            if (rbSleep.isChecked == true) {
                listTime.addAll(calculateWakeUp(time))
            } else {
                listTime.addAll(calculateSleep(time))
            }
            adapter!!.notifyDataSetChanged()
        }
        rbWakeup.setOnClickListener { v ->
            listTime.clear()
            if (rbSleep.isChecked == true) {
                listTime.addAll(calculateWakeUp(time))
            } else {
                listTime.addAll(calculateSleep(time))
            }
            adapter!!.notifyDataSetChanged()
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
                adapter!!.notifyDataSetChanged()
                tvTime.text = "${String.format("%02d", selectedHour)}:${String.format("%02d", selectedMinute)}"
            }, hour, minute, true)//Yes 24 hour time
            mTimePicker.setTitle("Select Time")
            mTimePicker.show()
        })


    }

    fun calculateWakeUp(time: TimeClass): ArrayList<TimeClass> {
        val listSleepTime: ArrayList<TimeClass> = ArrayList()

        var hour: Int
        for (i in 1..10) {
            var minute = time.Hour * 60 + time.Minute + timetosleep
            minute = minute + (cyclerdur * i)
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
        val listSleepTime: ArrayList<TimeClass> = ArrayList()

        var hour: Int
        for (i in 1..10) {
            var minute = time.Hour * 60 + time.Minute - timetosleep
            minute = minute - (cyclerdur * i)
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.mainmenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.itemSetting ->{
                val i = Intent(applicationContext, SettingActivity::class.java)
                startActivity(i)
            }
            R.id.itemSetting ->{
                // do nothing for now
            }
        }

        return true
    }

    override fun onResume() {
        super.onResume()
        // SHARED PREFERENCES
        val setting = SettingPref.load(this)
        if (setting == null) {
            SettingPref.clearAll(this@MainActivity)
        } else {
            am = setting.settingList[0].value
            cyclerdur = setting.settingList[1].value
            timetosleep = setting.settingList[2].value
            Log.i("SharePreference", "" + am + " " + cyclerdur + " " + timetosleep)
        }

        listTime.clear()
        if (rbSleep.isChecked == true) {
            listTime.addAll(calculateWakeUp(time))
        } else {
            listTime.addAll(calculateSleep(time))
        }
        adapter!!.notifyDataSetChanged()
    }

}
