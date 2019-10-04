package com.mobiledevelopment.ucrefillsystem.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mobiledevelopment.ucrefillsystem.HomeActivity
import com.mobiledevelopment.ucrefillsystem.R
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUpFragment : Fragment(), View.OnClickListener {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btn_signup_submit.setOnClickListener(this)
        btn_signup_backtologin.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_signup_submit -> {
                if (checkData()) {
                    if (submitData()) {
                        startActivity(Intent(context, HomeActivity::class.java))
                    }
                }
            }
            R.id.btn_signup_backtologin -> {
                fragmentManager!!.beginTransaction()
                    .replace(R.id.fl_login_container, LoginFragment()).commit()
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
