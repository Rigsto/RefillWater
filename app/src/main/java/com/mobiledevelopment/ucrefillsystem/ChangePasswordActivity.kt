package com.mobiledevelopment.ucrefillsystem

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.mobiledevelopment.ucrefillsystem.helper.SharedPreferenceKey
import com.mobiledevelopment.ucrefillsystem.helper.gone
import com.mobiledevelopment.ucrefillsystem.helper.sharePref
import com.mobiledevelopment.ucrefillsystem.helper.visible
import com.mobiledevelopment.ucrefillsystem.network.ApiRepository
import com.mobiledevelopment.ucrefillsystem.presenter.MessagePresenter
import com.mobiledevelopment.ucrefillsystem.viewinterface.MessageView
import kotlinx.android.synthetic.main.activity_change_password.*

class ChangePasswordActivity : AppCompatActivity(), MessageView, View.OnClickListener {
    private lateinit var messagePresenter: MessagePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        supportActionBar?.hide()
        tb_change_password.setNavigationOnClickListener {
            onBackPressed()
        }

        btn_cpass_save.setOnClickListener(this)

        messagePresenter = MessagePresenter(this, ApiRepository(), Gson())
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_cpass_save -> {
                when (checkData()) {
                    1 -> Toast.makeText(this, "Please input all datas", Toast.LENGTH_SHORT).show()
                    2 -> Toast.makeText(this, "Wrong old password", Toast.LENGTH_SHORT).show()
                    3 -> Toast.makeText(
                        this,
                        "Password confirmation does not match",
                        Toast.LENGTH_SHORT
                    ).show()
                    4 -> Toast.makeText(
                        this,
                        "Min password length is 8 characters",
                        Toast.LENGTH_SHORT
                    ).show()
                    0 -> {
                        messagePresenter.changePassword(
                            sharePref().getString(SharedPreferenceKey.ACCESS_TOKEN, "")!!,
                            sharePref().getString(SharedPreferenceKey.EMAIL_KEY, "")!!,
                            edt_cpass_new.text.toString()
                        )
                    }
                }
            }
        }
    }

    private fun checkData(): Int {
        if (edt_cpass_old.text.isNullOrEmpty()) return 1
        if (edt_cpass_new.text.isNullOrEmpty()) return 1
        if (edt_cpass_new2.text.isNullOrEmpty()) return 1

        val oldPass = sharePref().getString(SharedPreferenceKey.PASSWORD_KEY, "")!!

        if (edt_cpass_old.text.toString() != oldPass) return 2
        if (edt_cpass_new.text.toString() != edt_cpass_new2.text.toString()) return 3
        if (edt_cpass_new.text.length < 8) return 4
        return 0
    }

    override fun showMessageLoading() {
        pb_cpass_save.visible()
        btn_cpass_save.gone()
    }

    override fun hideMessageLoading() {
        pb_cpass_save.gone()
        btn_cpass_save.visible()
    }

    override fun getMessage(message: String) {
        if (message == "Password changed, you can log in") {
            Toast.makeText(this, "Update Success", Toast.LENGTH_SHORT).show()

            sharePref().edit()
                .putString(SharedPreferenceKey.PASSWORD_KEY, edt_cpass_new.text.toString())
                .apply()

            startActivity(
                Intent(this, HomeActivity::class.java).putExtra(
                    "goto",
                    "account"
                )
            )
            finish()
        }
    }
}
