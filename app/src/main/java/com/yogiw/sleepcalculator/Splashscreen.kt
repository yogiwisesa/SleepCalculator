package com.yogiw.sleepcalculator

import android.content.Intent
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton

class Splashscreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        Handler().postDelayed({
            val i = Intent(application, MainActivity::class.java)
            startActivity(i)
            finish()
        }, 2000)
    }
}
