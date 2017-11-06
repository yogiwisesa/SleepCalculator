package com.yogiw.sleepcalculator.Activity

import android.content.Intent
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.yogiw.sleepcalculator.Helper.FirsttimePref
import com.yogiw.sleepcalculator.R

class Splashscreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        Handler().postDelayed({

            val first = FirsttimePref.load(this)

            var i :Intent
            if (first != null) {
                 i = Intent(application, MainActivity::class.java)
            } else {
                 i = Intent(application, WelcomeActivity::class.java)
            }
            startActivity(i)
            finish()
        }, 2000)


    }
}
