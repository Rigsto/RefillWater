package com.mobiledevelopment.ucrefillsystem.fragment.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.mobiledevelopment.ucrefillsystem.HomeActivity
import com.mobiledevelopment.ucrefillsystem.R
import com.mobiledevelopment.ucrefillsystem.helper.*
import com.mobiledevelopment.ucrefillsystem.model.User
import com.mobiledevelopment.ucrefillsystem.network.ApiRepository
import com.mobiledevelopment.ucrefillsystem.presenter.LoginPresenter
import com.mobiledevelopment.ucrefillsystem.viewinterface.LoginView
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment(), View.OnClickListener, LoginView {
    private lateinit var presenter: LoginPresenter

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
                presenter.login("", "")
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

    override fun loginSuccess(user: User) {
        context!!.sharePref().edit()
            .putString(SharedPreferenceKey.fullname, user.name)
            .putString(SharedPreferenceKey.api, user.api)
            .putString(SharedPreferenceKey.email, user.email)
            .putString(SharedPreferenceKey.nim, user.nim)
            .apply()

        startActivity(Intent(activity, HomeActivity::class.java))
        activity?.finish()
    }

    override fun loginFailed() {

    }
}
