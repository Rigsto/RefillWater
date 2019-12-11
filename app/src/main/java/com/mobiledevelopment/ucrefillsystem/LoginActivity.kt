package com.mobiledevelopment.ucrefillsystem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.mobiledevelopment.ucrefillsystem.fragment.login.LoginFragment

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        supportFragmentManager.beginTransaction().replace(R.id.fl_container_login, LoginFragment())
            .commit()



    }
}

