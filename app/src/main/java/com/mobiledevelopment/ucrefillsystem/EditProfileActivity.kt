package com.mobiledevelopment.ucrefillsystem

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mobiledevelopment.ucrefillsystem.helper.SharedPreferenceKey
import com.mobiledevelopment.ucrefillsystem.helper.sharePref
import kotlinx.android.synthetic.main.activity_edit_profile.*

class EditProfileActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        supportActionBar?.hide()
        tb_edit_profile.setNavigationOnClickListener {
            onBackPressed()
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
