package com.mobiledevelopment.ucrefillsystem.fragment.home


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.mobiledevelopment.ucrefillsystem.EditProfileActivity
import com.mobiledevelopment.ucrefillsystem.LoginActivity
import com.mobiledevelopment.ucrefillsystem.R
import com.mobiledevelopment.ucrefillsystem.helper.*
import com.mobiledevelopment.ucrefillsystem.network.ApiRepository
import com.mobiledevelopment.ucrefillsystem.presenter.auth.LogoutPresenter
import com.mobiledevelopment.ucrefillsystem.viewinterface.auth.LogoutView
import kotlinx.android.synthetic.main.fragment_account.*

/**
 * A simple [Fragment] subclass.
 */
class AccountFragment : Fragment(), View.OnClickListener,
    LogoutView {
    private lateinit var logoutPresenter: LogoutPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        tv_account_editprofile.setOnClickListener(this)
        ll_account_report.setOnClickListener(this)
        ll_account_howtouse.setOnClickListener(this)
        ll_account_about.setOnClickListener(this)
        ll_account_logout.setOnClickListener(this)

        logoutPresenter =
            LogoutPresenter(
                this,
                ApiRepository(),
                Gson()
            )

        sw_notifications.setOnCheckedChangeListener { _, isChecked ->
            context!!.sharePref().edit().putBoolean(SharedPreferenceKey.notification, isChecked)
                .apply()
        }

        val sp = context!!.sharePref()
        tv_account_name.text = "Hi, ${sp.getString(SharedPreferenceKey.NAME_KEY, "")}!"
        tv_account_email.text = sp.getString(SharedPreferenceKey.EMAIL_KEY, "")
        val sw = sp.getBoolean(SharedPreferenceKey.notification, false)
        sw_notifications.isChecked = sw
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.tv_account_editprofile -> {
                startActivity(Intent(activity, EditProfileActivity::class.java))
            }
            R.id.ll_account_logout -> {
                logoutPresenter.logout(
                    context?.sharePref()?.getString(
                        SharedPreferenceKey.ACCESS_TOKEN,
                        ""
                    )!!
                )
            }
        }
    }

    override fun showLoading() {
        pb_logout.visible()
        ll_account_logout.invisible()
    }

    override fun hideLoading() {
        pb_logout.gone()
        ll_account_logout.visible()
    }

    override fun logoutSuccess() {
        val sp = context?.sharePref()

        sp?.edit()?.putString(SharedPreferenceKey.ACCESS_TOKEN, "")
            ?.putString(SharedPreferenceKey.REFRESH_TOKEN, "")
            ?.apply()

        startActivity(Intent(activity, LoginActivity::class.java))
        activity?.finish()
    }

    override fun logoutFailed(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }
}
