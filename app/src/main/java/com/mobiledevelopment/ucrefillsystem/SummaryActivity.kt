package com.mobiledevelopment.ucrefillsystem

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.mobiledevelopment.ucrefillsystem.adapter.history.HistoryAdapter
import com.mobiledevelopment.ucrefillsystem.helper.SharedPreferenceKey
import com.mobiledevelopment.ucrefillsystem.helper.sharePref
import com.mobiledevelopment.ucrefillsystem.model.History
import com.mobiledevelopment.ucrefillsystem.network.ApiRepository
import com.mobiledevelopment.ucrefillsystem.presenter.HistoryPresenter
import com.mobiledevelopment.ucrefillsystem.viewinterface.HistoryView
import kotlinx.android.synthetic.main.activity_summary.*

class SummaryActivity : AppCompatActivity(), HistoryView {
    private lateinit var historyAdapter: HistoryAdapter
    private lateinit var historyPresenter: HistoryPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)

        supportActionBar?.hide()
        tb_summary.setNavigationOnClickListener {
            onBackPressed()
        }

        historyPresenter = HistoryPresenter(this, ApiRepository(), Gson())
        historyPresenter.getHistory(sharePref().getString(SharedPreferenceKey.ACCESS_TOKEN, "")!!)

        tv_summary_seeall.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java).putExtra("goto", "history"))
            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showHistoryList(list: List<History>) {
        historyAdapter = HistoryAdapter(this, list)
        historyAdapter.count = 2
        historyAdapter.type = 1

        rv_summary_short.adapter = historyAdapter
        rv_summary_short.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_summary_short.setHasFixedSize(true)
    }
}
