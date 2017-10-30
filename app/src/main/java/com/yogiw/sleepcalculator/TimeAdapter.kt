package com.yogiw.sleepcalculator

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

/**
 * Created by Yogi Wisesa on 10/30/2017.
 */
class TimeAdapter: RecyclerView.Adapter<TimeAdapter.ViewHolder>{
    var list: List<TimeClass>? = null
    var context: Context? = null

    constructor( context: Context?, list: List<TimeClass>?) : super() {
        this.list = list
        this.context = context
    }


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        var inflater = LayoutInflater.from(context).inflate(R.layout.timeitem, parent, false)

        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        var hour = list!!.get(position).Hour
        var min = list!!.get(position).Minute
        var durHour = list!!.get(position).durHour
        var durMin = list!!.get(position).durMinute
        var cycles = list!!.get(position).cycles


        holder!!.tvTime.text = "$hour:$min"
        if (min > 0){
           if (hour > 1){
               holder!!.tvDuration.text = "Sleep for $durHour hours and $durMin"
           } else {
               holder!!.tvDuration.text = "Sleep for $durHour hour and $durMin"
           }
        } else {
            if (hour > 1){
                holder!!.tvDuration.text = "Sleep for $durHour hours"
            } else {
                holder!!.tvDuration.text = "Sleep for $durHour hour"
            }
        }
       if (cycles > 1){
           holder!!.tvCycles.text = "$cycles Cycles"
       } else {
           holder!!.tvCycles.text = "$cycles Cycle"
       }
    }

    override fun getItemCount(): Int {
        return list!!.size
    }


    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var tvTime = itemView?.findViewById<View>(R.id.tvTime) as TextView
        var tvDuration = itemView?.findViewById<View>(R.id.tvDuration) as TextView
        var tvCycles = itemView?.findViewById<View>(R.id.tvCycles) as TextView
    }


}