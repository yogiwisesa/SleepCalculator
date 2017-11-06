package com.yogiw.sleepcalculator.Adapter

import android.content.Context
import android.content.Intent
import android.provider.AlarmClock
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.yogiw.sleepcalculator.Model.TimeClass
import com.yogiw.sleepcalculator.R

/**
 * Created by Yogi Wisesa on 10/30/2017.
 */
class TimeAdapter(var context: Context?, var list: List<TimeClass>?) : RecyclerView.Adapter<TimeAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context).inflate(R.layout.timeitem, parent, false)

        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val hour = list!!.get(position).Hour
        val min = list!!.get(position).Minute
        val durHour = list!!.get(position).durHour
        val durMin = list!!.get(position).durMinute
        val cycles = list!!.get(position).cycles
        val wakeup = list!!.get(position).wakeup
        Log.i("adapter",""+ hour)

        holder!!.tvTime.text = "${String.format("%02d", hour)}:${String.format("%02d", min)}" // format to 2 digits of string
        if (min > 0) {
            if (hour > 1) {
                if (min > 1){
                    holder.tvDuration.text = "Sleep for $durHour hours and $durMin minutes"
                } else {
                    holder.tvDuration.text = "Sleep for $durHour hours and $durMin minute"
                }
            } else {
                if (min > 1){
                    holder.tvDuration.text = "Sleep for $durHour hour and $durMin minutes"
                } else {
                    holder.tvDuration.text = "Sleep for $durHour hour and $durMin minutes"
                }
            }
        } else {
            if (hour > 1) {
                holder.tvDuration.text = "Sleep for $durHour hours"
            } else {
                holder.tvDuration.text = "Sleep for $durHour hour"
            }
        }
        if (cycles > 1) {
            holder.tvCycles.text = "$cycles Cycles"
            if (cycles == 5){
                holder.tvIdeal.visibility = View.VISIBLE
            } else {
                holder.tvIdeal.visibility = View.GONE
            }
        } else {
            holder.tvCycles.text = "$cycles Cycle"
        }

        if (wakeup){
            holder.ivAlarm.setOnClickListener { v ->
                val i= Intent(AlarmClock.ACTION_SET_ALARM)
                i.putExtra(AlarmClock.EXTRA_MESSAGE, "Wakeup now!! - Bedtime Calculator")
                i.putExtra(AlarmClock.EXTRA_HOUR, hour)
                i.putExtra(AlarmClock.EXTRA_MINUTES, min)
                context!!.startActivity(i)

            }
        } else {
            holder.ivAlarm.setOnClickListener { v ->
                val i= Intent(AlarmClock.ACTION_SET_ALARM)
                i.putExtra(AlarmClock.EXTRA_MESSAGE, "It's time to sleep :) - Bedtime Calculator")
                i.putExtra(AlarmClock.EXTRA_HOUR, hour)
                i.putExtra(AlarmClock.EXTRA_MINUTES, min)
                context!!.startActivity(i)

            }
        }


    }

    override fun getItemCount(): Int {
        return list!!.size
    }


    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var tvTime = itemView?.findViewById<View>(R.id.tvTime) as TextView
        var tvDuration = itemView?.findViewById<View>(R.id.tvDuration) as TextView
        var tvCycles = itemView?.findViewById<View>(R.id.tvCycles) as TextView
        var tvIdeal = itemView?.findViewById<View>(R.id.tvIdeal) as TextView
        var ivAlarm = itemView?.findViewById<View>(R.id.ivAlarm) as ImageView
    }
}