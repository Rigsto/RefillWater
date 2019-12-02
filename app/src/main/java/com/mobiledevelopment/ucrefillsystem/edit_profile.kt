package com.mobiledevelopment.ucrefillsystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_edit_profile.*

class edit_profile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        supportActionBar?.hide()
        val array = arrayOf("kamera","galeri")
        button.setOnClickListener{

            val builder = AlertDialog.Builder(this)

            builder.setItems(array,{_, which ->


            })
            val dialog: AlertDialog = builder.create()
            dialog.show()
    }
}}
