package com.mobiledevelopment.ucrefillsystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class account : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
        supportActionBar?.hide()
    }
}
