package com.mobiledevelopment.ucrefillsystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class activity_home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.hide()
    }
}
