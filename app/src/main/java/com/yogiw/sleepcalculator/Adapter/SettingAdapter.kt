package com.yogiw.sleepcalculator.Adapter

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.provider.AlarmClock
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Switch
import android.widget.TextView
import com.yogiw.sleepcalculator.Helper.SettingPref
import com.yogiw.sleepcalculator.Model.SettingClass
import com.yogiw.sleepcalculator.Model.SettingList
import com.yogiw.sleepcalculator.Model.TimeClass
import com.yogiw.sleepcalculator.R
import android.content.DialogInterface
import android.text.Editable
import android.widget.EditText



/**
 * Created by Yogi Wisesa on 10/31/2017.
 */
class SettingAdapter(var context: Context?, var list: SettingList?) : RecyclerView.Adapter<SettingAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context).inflate(R.layout.settingitem, parent, false)

        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder!!.tvName.text = list!!.settingList[position].name
        holder!!.tvDesc.text = list!!.settingList[position].desc

        if (position==0){
            holder!!.swOn.visibility = View.VISIBLE
        }

        // SHARED PREFERENCES

        holder.swOn.isChecked = list!!.settingList[0].value == 1

        holder!!.swOn.setOnClickListener {
            if (holder.swOn.isChecked){
                list!!.settingList[0].value = 1
            } else {
                list!!.settingList[0].value = 0
            }
            SettingPref.save(list, context?.applicationContext)
            Log.i("SharePreferenceX", ""+list!!.settingList[0].value + " " + list!!.settingList[1].value + " " + list!!.settingList[2].value)
        }

        holder!!.container.setOnClickListener{
            if (position == 1){
                val alertDialog = AlertDialog.Builder(context).create()
                val input = EditText(context)
                input.hint = "90"
                alertDialog.setTitle("Sleep cycle duration (in minutes)")
                //alertDialog.setMessage("")
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "SAVE") { dialog, id ->
                    list!!.settingList[1].value = input.text.toString().toInt()
                    SettingPref.save(list, context)
                    Log.i("SharePreferenceX", ""+list!!.settingList[0].value + " " + list!!.settingList[1].value + " " + list!!.settingList[2].value)
                }
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "CANCEL") { dialog, id ->
                    alertDialog.dismiss()
                }
                alertDialog.setView(input)
                alertDialog.show()
            } else {
                val alertDialog = AlertDialog.Builder(context).create()
                val input = EditText(context)
                input.hint = "15"
                alertDialog.setTitle("Time you takes to fall asleep (in minutes)")
                //alertDialog.setMessage("")
                alertDialog.setView(input)
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "SAVE") { dialog, id ->
                    list!!.settingList[2 ].value = input.text.toString().toInt()
                    SettingPref.save(list, context)
                    Log.i("SharePreferenceX", ""+list!!.settingList[0].value + " " + list!!.settingList[1].value + " " + list!!.settingList[2].value)
                }
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "CANCEL") { dialog, id ->
                    alertDialog.dismiss()
                }
                alertDialog.show()
            }
        }
    }

    override fun getItemCount(): Int {
        return list!!.settingList.size
    }


    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var tvName = itemView?.findViewById<View>(R.id.tvName) as TextView
        var tvDesc = itemView?.findViewById<View>(R.id.tvDescription) as TextView
        var swOn = itemView?.findViewById<View>(R.id.swOn) as Switch
        var container = itemView?.findViewById<View>(R.id.container) as LinearLayout
    }
}