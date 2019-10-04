package com.mobiledevelopment.ucrefillsystem.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mobiledevelopment.ucrefillsystem.HomeActivity
import com.mobiledevelopment.ucrefillsystem.R
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment(), View.OnClickListener {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btn_login_signup.setOnClickListener(this)
        btn_login_login.setOnClickListener(this)
        tv_login_forgotpass.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_login_signup -> {
                fragmentManager!!.beginTransaction()
                    .replace(R.id.fl_login_container, SignUpFragment()).commit()
            }
            R.id.btn_login_login -> {
                if (checkData()) {
                    if (submitData()) {
                        startActivity(Intent(context, HomeActivity::class.java))
                    }
                }
            }
            R.id.tv_login_forgotpass -> {

            }
        }
    }

    private fun checkData(): Boolean {
        return true
    }

    private fun submitData(): Boolean {
        return true
    }
}
