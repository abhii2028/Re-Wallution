package com.rewallution.screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.rewallution.R
import com.rewallution.utility.setUIDesign

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUIDesign(this@SplashActivity,shouldChange = true)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            startActivity(Intent(this@SplashActivity,
                HomeActivity::class.java))
            finish()
        },3000)
    }
}
