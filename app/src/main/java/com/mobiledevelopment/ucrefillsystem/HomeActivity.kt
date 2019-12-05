package com.mobiledevelopment.ucrefillsystem

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mobiledevelopment.ucrefillsystem.fragment.home.AccountFragment
import com.mobiledevelopment.ucrefillsystem.fragment.home.HistoryFragment
import com.mobiledevelopment.ucrefillsystem.fragment.home.HomeFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var fragment: Fragment
    private var KEY = "savefragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        actionBar?.hide()
        bottom_navigation.setOnNavigationItemSelectedListener(this)

        if (savedInstanceState != null)
            fragment = supportFragmentManager.getFragment(savedInstanceState, KEY)!!
        else
            fragment = HomeFragment()

        loadFragment()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
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
}
