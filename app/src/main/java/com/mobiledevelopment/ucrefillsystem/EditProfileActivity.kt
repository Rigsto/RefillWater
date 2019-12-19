package com.mobiledevelopment.ucrefillsystem

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
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
import kotlinx.android.synthetic.main.activity_edit_profile.*

class EditProfileActivity : AppCompatActivity(), View.OnClickListener,
    AdapterView.OnItemClickListener, MessageView {
    private var chosen = ""
    private lateinit var messagePresenter: MessagePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        supportActionBar?.hide()
        tb_edit_profile.setNavigationOnClickListener {

        }

        tv_eprofile_change_password.setOnClickListener {
            startActivity(Intent(this, ChangePasswordActivity::class.java))
        }

        val name = sharePref().getString(SharedPreferenceKey.NAME_KEY, "")
        edt_eprofile_name.setText(name)
        val email = sharePref().getString(SharedPreferenceKey.EMAIL_KEY, "")
        edt_eprofile_email.setText(email)

        btn_eprofile_save.setOnClickListener(this)
//        spinner_major.setOnItemClickListener(this)

        val list = mutableListOf<String>()
        list.add("ACC")
        list.add("CB")
        list.add("COM")
        list.add("HTB")
        list.add("IBM-IC")
        list.add("IBM-RC")
        list.add("IMT")
        list.add("INA")
        list.add("ISB")
        list.add("MED")
        list.add("PSY")
        list.add("VCD")

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

//        spinner_major.adapter = adapter

        messagePresenter = MessagePresenter(this, ApiRepository(), Gson())
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        chosen = parent.getItemAtPosition(position).toString()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_eprofile_save -> {
                if (checkData()) {
                    messagePresenter.editProfile(
                        sharePref().getString(SharedPreferenceKey.ACCESS_TOKEN, "")!!,
                        edt_eprofile_name.text.toString(),
                        sharePref().getString(SharedPreferenceKey.PASSWORD_KEY, "")!!,
                        sharePref().getString(SharedPreferenceKey.GENDER_KEY, "")!!,
                        sharePref().getString(SharedPreferenceKey.MAJOR_KEY, "")!!
                    )

                } else {
                    Toast.makeText(this, "Please input your name!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun checkData(): Boolean {
        if (edt_eprofile_name.text.isNullOrEmpty()) return false
        return true
    }

    override fun showMessageLoading() {
        pb_eprofile_save.visible()
        btn_eprofile_save.gone()
    }

    override fun hideMessageLoading() {
        pb_eprofile_save.gone()
        btn_eprofile_save.visible()
    }

    override fun getMessage(message: String) {
        if (message == "Update success") {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

            sharePref().edit()
                .putString(SharedPreferenceKey.NAME_KEY, edt_eprofile_name.text.toString())
                .apply()

            startActivity(
                Intent(this, HomeActivity::class.java).putExtra(
                    "goto",
                    "account"
                )
            )
            finish()
        } else {
            hideMessageLoading()
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }
}
