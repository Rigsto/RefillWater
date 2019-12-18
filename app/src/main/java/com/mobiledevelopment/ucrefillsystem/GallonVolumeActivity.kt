package com.mobiledevelopment.ucrefillsystem

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.mobiledevelopment.ucrefillsystem.adapter.gallon_volume.GallonVolumeAdapter
import com.mobiledevelopment.ucrefillsystem.model.Gallon
import com.mobiledevelopment.ucrefillsystem.network.ApiRepository
import com.mobiledevelopment.ucrefillsystem.presenter.GallonVolumePresenter
import com.mobiledevelopment.ucrefillsystem.viewinterface.GallonVolumeView
import kotlinx.android.synthetic.main.activity_gallon_volume.*

class GallonVolumeActivity : AppCompatActivity(), GallonVolumeView {
    private lateinit var gallonVolumeAdapter: GallonVolumeAdapter
    private lateinit var gallonVolumePresenter: GallonVolumePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallon_volume)

        supportActionBar?.hide()
        tb_gallon_volume.setNavigationOnClickListener {
            onBackPressed()
        }

        gallonVolumePresenter = GallonVolumePresenter(this, ApiRepository(), Gson())
        gallonVolumePresenter.getGallons()

        gallonVolumeAdapter = GallonVolumeAdapter(this)
        rv_gallon_volume.adapter = gallonVolumeAdapter
        rv_gallon_volume.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_gallon_volume.setHasFixedSize(true)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun getGallons(gallons: List<Gallon>) {
        gallonVolumeAdapter.addList(gallons)
    }
}
