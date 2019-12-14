package com.mobiledevelopment.ucrefillsystem.fragment.home


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mobiledevelopment.ucrefillsystem.EditProfileActivity
import com.mobiledevelopment.ucrefillsystem.R
import com.mobiledevelopment.ucrefillsystem.helper.SharedPreferenceKey
import com.mobiledevelopment.ucrefillsystem.helper.sharePref
import kotlinx.android.synthetic.main.fragment_account.*

/**
 * A simple [Fragment] subclass.
 */
class AccountFragment : Fragment(), View.OnClickListener {

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
        }
    }
}
