package com.mobiledevelopment.ucrefillsystem

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.mobiledevelopment.ucrefillsystem.fragment.refill.ScanFragment

class RefillActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_refill)

        loadFragment(ScanFragment())
    }

    fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fl_refill_container, fragment)
            .commit()
    }
}
