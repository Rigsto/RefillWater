package com.mobiledevelopment.ucrefillsystem.fragment.login

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
import com.mobiledevelopment.ucrefillsystem.model.User
import com.mobiledevelopment.ucrefillsystem.model.response.LoginResponse
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
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = LoginPresenter(this, ApiRepository(), Gson())
        tryAutoLogin()

        tv_login_to_register.setOnClickListener(this)
        tv_forgot_password.setOnClickListener(this)
        btn_login.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.tv_login_to_register -> {
                fragmentManager?.beginTransaction()
                    ?.replace(R.id.fl_container_login, RegisterFragment())?.commit()
            }
            R.id.tv_forgot_password -> {

            }
            R.id.btn_login -> {
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
        }
    }

    private fun checkData(): Boolean {
        return (edt_login_username.text.isNotEmpty() && edt_login_password.text.isNotEmpty())
    }

    private fun submitData() {
        email = edt_login_username.text.toString()
        password = edt_login_password.text.toString()
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
        pb_login.visible()
        btn_login.invisible()
    }

    override fun hideLoading() {
        pb_login.gone()
        btn_login.visible()
    }

    override fun loginSuccess(name : String?, balance : String?) {
        val sp = context?.sharePref()

        if (auto == 0) {
            sp?.edit()?.putString(SharedPreferenceKey.NAME_KEY,name)
                ?.putString(SharedPreferenceKey.EMAIL_KEY, email)
                ?.putString(SharedPreferenceKey.PASSWORD_KEY, password)
                ?.putString(SharedPreferenceKey.API_KEY,balance)?.apply()
        }

        sp?.edit()?.putInt(SharedPreferenceKey.MONEY_KEY, 200!!)?.apply()

        startActivity(Intent(context, HomeActivity::class.java))
        activity?.finish()
    }

    override fun loginFailed(message : String) {
        val sp = context?.sharePref()
        sp?.edit()?.clear()?.apply()

        auto = 0
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
