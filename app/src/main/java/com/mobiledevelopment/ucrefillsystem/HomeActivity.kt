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

class HomeActivity(private val gotoFragment: Fragment? = null) : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottom_navigation.setOnNavigationItemSelectedListener(this)

        if (gotoFragment == null)
            loadFragment(HomeFragment())
        else
            loadFragment(gotoFragment)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.bnv_item_home -> {
                loadFragment(HomeFragment())
                return true
            }
            R.id.bnv_item_history -> {
                loadFragment(HistoryFragment())
                return true
            }
            R.id.bnv_item_account -> {
                loadFragment(AccountFragment())
                return true
            }
        }
        return false
    }

    private fun loadFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.fl_home_contaier, fragment).commit()
    }
}
