package com.mobiledevelopment.ucrefillsystem

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobiledevelopment.ucrefillsystem.adapter.history.HistoryAdapter
import com.mobiledevelopment.ucrefillsystem.helper.getDummyHistoryData
import kotlinx.android.synthetic.main.activity_summary.*

class SummaryActivity : AppCompatActivity() {
    private lateinit var historyAdapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)

        supportActionBar?.hide()
        tb_summary.setNavigationOnClickListener {
            onBackPressed()
        }

        historyAdapter = HistoryAdapter(this, getDummyHistoryData())
        historyAdapter.count = 2
        historyAdapter.type = 1

        rv_summary_short.adapter = historyAdapter
        rv_summary_short.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_summary_short.setHasFixedSize(true)

        tv_summary_seeall.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java).putExtra("goto", "history"))
            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
