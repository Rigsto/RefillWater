package com.mobiledevelopment.ucrefillsystem

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.karumi.dexter.listener.PermissionRequestErrorListener
import com.karumi.dexter.listener.single.PermissionListener
import com.mobiledevelopment.ucrefillsystem.fragment.home.AccountFragment
import com.mobiledevelopment.ucrefillsystem.fragment.home.HistoryFragment
import com.mobiledevelopment.ucrefillsystem.fragment.home.HomeFragment
import com.mobiledevelopment.ucrefillsystem.fragment.home.ScanFragment
import com.mobiledevelopment.ucrefillsystem.helper.gone
import com.mobiledevelopment.ucrefillsystem.helper.visible
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var fragment: Fragment
    private lateinit var cameraPermissionListener: PermissionListener
    private lateinit var errorListener: PermissionRequestErrorListener
    private var KEY = "savefragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportActionBar?.hide()
        bottom_navigation.setOnNavigationItemSelectedListener(this)
        createPermissionListeners()

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
                fragment = ScanFragment()
                loadFragment()
                img_bnv.gone()
                return true
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

    private fun createPermissionListeners() {

    }
}
