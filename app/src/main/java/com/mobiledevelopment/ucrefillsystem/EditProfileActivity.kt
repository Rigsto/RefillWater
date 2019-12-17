package com.mobiledevelopment.ucrefillsystem

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.mobiledevelopment.ucrefillsystem.helper.*
import com.mobiledevelopment.ucrefillsystem.network.ApiRepository
import com.mobiledevelopment.ucrefillsystem.presenter.LoginPresenter
import com.mobiledevelopment.ucrefillsystem.presenter.ProfilePresenter
import com.mobiledevelopment.ucrefillsystem.viewinterface.ProfileView
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.fragment_login.*

class EditProfileActivity : AppCompatActivity(), View.OnClickListener, ProfileView {
    override fun updatesuccess() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoading() {
        pb_login.visible()
        btn_login.invisible()
    }

    override fun hideLoading() {
        pb_login.gone()
        btn_login.visible()
    }

    private lateinit var presenter: ProfilePresenter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        presenter = ProfilePresenter( this, ApiRepository(), Gson())

        supportActionBar?.hide()
        tb_edit_profile.setNavigationOnClickListener {
           presenter.edit(sharePref().getString(SharedPreferenceKey.EMAIL_KEY, ""))
        }

        tv_eprofile_change_password.setOnClickListener {
            startActivity(Intent(this, ChangePasswordActivity::class.java))
        }

        val name = sharePref().getString(SharedPreferenceKey.NAME_KEY, "")
        edt_eprofile_name.setText(name)
        val email = sharePref().getString(SharedPreferenceKey.EMAIL_KEY, "")
        edt_eprofile_email.setText(email)

        btn_eprofile_save.setOnClickListener(this)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_eprofile_save -> {
                if (checkData()) {
                    sharePref().edit()
                        .putString(SharedPreferenceKey.NAME_KEY, edt_eprofile_name.text.toString())
                        .putString(SharedPreferenceKey.EMAIL_KEY, edt_eprofile_email.text.toString())
                        .apply()

                    startActivity(
                        Intent(this, HomeActivity::class.java).putExtra(
                            "goto",
                            "account"
                        )
                    )
                    finish()
                } else {
                    Toast.makeText(this, "Please input your name and email!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun checkData(): Boolean {
        if (edt_eprofile_name.text.isNullOrEmpty()) return false
        if (edt_eprofile_email.text.isNullOrEmpty()) return false
        return true
    }
}
