package com.yogiw.sleepcalculator.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.yogiw.sleepcalculator.Adapter.SettingAdapter
import com.yogiw.sleepcalculator.Helper.SettingPref
import com.yogiw.sleepcalculator.Model.SettingClass
import com.yogiw.sleepcalculator.Model.SettingList
import com.yogiw.sleepcalculator.R
import kotlinx.android.synthetic.main.activity_setting.*


class SettingActivity : AppCompatActivity() {
    var am: Int = 0
    var cyclerdur: Int = 90
    var timetosleep: Int = 15
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        // SHARED PREFERENCES
        val setting = SettingPref.load(this)
        if (setting == null) {
            SettingPref.clearAll(this@SettingActivity)
        } else {
            am = setting.settingList[0].value
            cyclerdur = setting.settingList[1].value
            timetosleep = setting.settingList[2].value
            Log.i("SharePreference", "" + am + " " + cyclerdur + " " + timetosleep)
        }

        var listSetting: ArrayList<SettingClass> = ArrayList()

        listSetting.add(SettingClass("AM/ PM Mode", "Time in AM/PM format instead of 24 hours", am))
        listSetting.add(SettingClass("Sleep cycle Duration", "Average people's sleep cycle: 90min", cyclerdur))
        listSetting.add(SettingClass("Time you takes to fall asleep", "Most people need about 15minutes to fall asleep", timetosleep))


        var mlistSetting = SettingList(listSetting)


        var adapter = SettingAdapter(this, mlistSetting)
        rvSetting.adapter = adapter
        rvSetting.layoutManager = LinearLayoutManager(applicationContext)

    }
}
