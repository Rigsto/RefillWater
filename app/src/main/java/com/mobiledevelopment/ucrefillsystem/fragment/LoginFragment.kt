package com.mobiledevelopment.ucrefillsystem.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.mobiledevelopment.ucrefillsystem.HomeActivity
import com.mobiledevelopment.ucrefillsystem.R
import com.mobiledevelopment.ucrefillsystem.helper.*
import com.mobiledevelopment.ucrefillsystem.model.LoginResponse
import com.mobiledevelopment.ucrefillsystem.network.ApiRepository
import com.mobiledevelopment.ucrefillsystem.presenter.LoginPresenter
import com.mobiledevelopment.ucrefillsystem.viewinterface.LoginView
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment(), View.OnClickListener, LoginView {
    private lateinit var presenter: LoginPresenter
    private var auto: Int = 0
    private lateinit var email: String
    private lateinit var password: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = LoginPresenter(this, ApiRepository(), Gson())
        tryAutoLogin()

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
                    submitData()
                } else {
                    Toast.makeText(
                        context,
                        "Please input your email and password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            R.id.tv_login_forgotpass -> {

            }
        }
    }

    private fun checkData(): Boolean {
        return (edt_login_email.text.isNotEmpty() && edt_login_password.text.isNotEmpty())
    }

    private fun submitData() {
        email = edt_login_email.text.toString()
        password = edt_login_password.text.toString().md5()
        presenter.login(email, password)
    }

    private fun tryAutoLogin() {
        val sp = context?.sharePref()
        if (sp?.getString(SharedPreferenceKey.API_KEY, "") != "") {
            val email = sp?.getString(SharedPreferenceKey.EMAIL_KEY, "")
            val password = sp?.getString(SharedPreferenceKey.PASSWORD_KEY, "")

            auto = 1
            presenter.login(email!!, password!!)
        }
    }

    override fun showLoading() {
        if (auto == 0) {
            btn_login_login.invisible()
            pb_manuallogin.visible()
        } else if (auto == 1) {
            ll_manuallogin.invisible()
            pb_autologin.visible()
        }
    }

    override fun hideLoading() {
        if (auto == 0) {
            btn_login_login.visible()
            pb_manuallogin.gone()
        } else if (auto == 1) {
            ll_manuallogin.visible()
            pb_autologin.gone()
        }
    }

    override fun loginSuccess(data: LoginResponse) {
        val sp = context?.sharePref()

        if (auto == 0) {
            sp?.edit()?.putString(SharedPreferenceKey.NAME_KEY, data.name)
                ?.putString(SharedPreferenceKey.EMAIL_KEY, email)
                ?.putString(SharedPreferenceKey.PASSWORD_KEY, password)
                ?.putString(SharedPreferenceKey.API_KEY, data.api)?.apply()
        }

        sp?.edit()?.putInt(SharedPreferenceKey.MONEY_KEY, data.money!!)?.apply()

        startActivity(Intent(context, HomeActivity::class.java))
        activity?.finish()
    }

    override fun loginFailed() {
        val sp = context?.sharePref()
        sp?.edit()?.clear()?.apply()

        auto = 0
        Toast.makeText(context, "Login Failed! Please try again later", Toast.LENGTH_SHORT).show()
    }
}
