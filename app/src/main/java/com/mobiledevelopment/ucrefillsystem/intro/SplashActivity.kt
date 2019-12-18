package com.mobiledevelopment.ucrefillsystem.intro

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.mobiledevelopment.ucrefillsystem.HomeActivity
import com.mobiledevelopment.ucrefillsystem.LoginActivity
import com.mobiledevelopment.ucrefillsystem.R
import com.mobiledevelopment.ucrefillsystem.helper.SharedPreferenceKey
import com.mobiledevelopment.ucrefillsystem.helper.sharePref

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()

        Handler().postDelayed({
            val sp = sharePref().getBoolean(SharedPreferenceKey.ONBOARDING, false)
            if (!sp) {
                startActivity(Intent(this, OnboardingActivity::class.java))
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                finish()
            } else {
                val api = sharePref().getString(SharedPreferenceKey.ACCESS_TOKEN, "")
                if (api == "") {
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                } else {
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                }
            }
        }, 3000)
    }
}
