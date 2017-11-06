package com.yogiw.sleepcalculator.Model

/**
 * Created by Yogi Wisesa on 10/30/2017.
 */
data class TimeClass(
        var Hour: Int,
        var Minute: Int,
        var durHour: Int = 0,
        var durMinute: Int = 0,
        var cycles: Int = 0,
        var wakeup: Boolean = true
)