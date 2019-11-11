package com.mobiledevelopment.ucrefillsystem

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mobiledevelopment.ucrefillsystem.fragment.login.LoginFragment
import com.mobiledevelopment.ucrefillsystem.model.User

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        supportFragmentManager.beginTransaction().replace(R.id.fl_container_login, LoginFragment())
            .commit()
    }

    interface LoginView {
        fun showLoading()
        fun hideLoading()
        fun showNameList(data : List<User>)
    }
}
