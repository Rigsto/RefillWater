package com.mobiledevelopment.ucrefillsystem

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.karumi.dexter.listener.PermissionRequestErrorListener
import com.karumi.dexter.listener.single.PermissionListener
import com.mobiledevelopment.ucrefillsystem.fragment.home.AccountFragment
import com.mobiledevelopment.ucrefillsystem.fragment.home.HistoryFragment
import com.mobiledevelopment.ucrefillsystem.fragment.home.HomeFragment
import com.mobiledevelopment.ucrefillsystem.helper.visible
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var fragment: Fragment
    private var doubleBackToExitPressedOnce = false
    private lateinit var cameraPermissionListener: PermissionListener
    private lateinit var errorListener: PermissionRequestErrorListener
    private var KEY = "savefragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportActionBar?.hide()
        bottom_navigation.setOnNavigationItemSelectedListener(this)

        if (savedInstanceState != null)
            fragment = supportFragmentManager.getFragment(savedInstanceState, KEY)!!
        else
            fragment = HomeFragment()

        if (intent != null) {
            if (intent.getStringExtra("goto") != null) {
                when (intent.getStringExtra("goto")) {
                    "history" -> {
                        fragment = HistoryFragment()
                        bottom_navigation.menu.getItem(1).setChecked(true)
                    }
                    "account" -> {
                        fragment = AccountFragment()
                        bottom_navigation.menu.getItem(4).setChecked(true)
                    }
                }
            }
        }

        loadFragment()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        img_bnv.visible()
        when (item.itemId){
            R.id.bnv_home -> {
                fragment = HomeFragment()
                loadFragment()
                return true
            }
            R.id.bnv_history -> {
                fragment = HistoryFragment()
                loadFragment()
                return true
            }
            R.id.bnv_scan -> {
                startActivity(Intent(this, ScanActivity::class.java))
            }
            R.id.bnv_cart -> {

            }
            R.id.bnv_profile -> {
                fragment = AccountFragment()
                loadFragment()
                return true
            }
        }

        return false
    }

    private fun loadFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.fl_container_home, fragment)
            .commit()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        supportFragmentManager.putFragment(outState, KEY, fragment)
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            finish()
            return
        }

        Toast.makeText(this, "Press again to exit!", Toast.LENGTH_SHORT).show()
        this.doubleBackToExitPressedOnce = true

        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }
}
