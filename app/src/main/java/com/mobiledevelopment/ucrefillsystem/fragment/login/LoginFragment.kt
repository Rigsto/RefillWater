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
import com.mobiledevelopment.ucrefillsystem.network.ApiRepository
import com.mobiledevelopment.ucrefillsystem.presenter.auth.LoginPresenter
import com.mobiledevelopment.ucrefillsystem.viewinterface.auth.LoginView
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment(), View.OnClickListener,
    LoginView {
    private lateinit var presenter: LoginPresenter
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

        presenter =
            LoginPresenter(
                this,
                ApiRepository(),
                Gson()
            )
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
        if (sp?.getString(SharedPreferenceKey.ACCESS_TOKEN, "") != "") {
            if (sp?.getString(SharedPreferenceKey.NAME_KEY, "") != "") {
                startActivity(Intent(context, HomeActivity::class.java))
                activity?.finish()
            } else {
                showLoading()
                presenter.getProfile(sp.getString(SharedPreferenceKey.ACCESS_TOKEN, "")!!)
            }
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

    override fun loginSuccess(accToken: String, refToken: String) {
        val sp = context?.sharePref()

        sp?.edit()?.putString(SharedPreferenceKey.ACCESS_TOKEN, accToken)
            ?.putString(SharedPreferenceKey.REFRESH_TOKEN, refToken)
            ?.apply()

        presenter.getProfile(accToken)
    }

    override fun loginFailed(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun loadProfile(user: User) {
        val sp = context?.sharePref()

        sp?.edit()?.putString(SharedPreferenceKey.NAME_KEY, user.name)
            ?.putString(SharedPreferenceKey.EMAIL_KEY, user.email)
            ?.putString(SharedPreferenceKey.GENDER_KEY, user.gender)
            ?.putString(SharedPreferenceKey.MAJOR_KEY, user.majors)
            ?.putInt(SharedPreferenceKey.MONEY_KEY, user.balance)
            ?.putString(SharedPreferenceKey.PASSWORD_KEY, edt_login_password.text.toString())
            ?.apply()

        startActivity(Intent(context, HomeActivity::class.java))
        activity?.finish()
    }
}
