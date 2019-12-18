package com.mobiledevelopment.ucrefillsystem

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobiledevelopment.ucrefillsystem.adapter.gallon_volume.GallonVolumeAdapter
import kotlinx.android.synthetic.main.activity_gallon_volume.*

class GallonVolumeActivity : AppCompatActivity() {
    private lateinit var gallonVolumeAdapter: GallonVolumeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallon_volume)

        supportActionBar?.hide()
        tb_gallon_volume.setNavigationOnClickListener {
            onBackPressed()
        }

        gallonVolumeAdapter = GallonVolumeAdapter(this, mutableListOf())
        rv_gallon_volume.adapter = gallonVolumeAdapter
        rv_gallon_volume.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_gallon_volume.setHasFixedSize(true)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
