package com.mobiledevelopment.ucrefillsystem.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.mobiledevelopment.ucrefillsystem.R
import com.mobiledevelopment.ucrefillsystem.adapter.HistoryAdapter
import com.mobiledevelopment.ucrefillsystem.helper.*
import com.mobiledevelopment.ucrefillsystem.model.History
import com.mobiledevelopment.ucrefillsystem.model.Refill
import com.mobiledevelopment.ucrefillsystem.model.TopUp
import com.mobiledevelopment.ucrefillsystem.network.ApiRepository
import com.mobiledevelopment.ucrefillsystem.presenter.HistoryPresenter
import com.mobiledevelopment.ucrefillsystem.viewinterface.HistoryView
import kotlinx.android.synthetic.main.fragment_history.*

class HistoryFragment : Fragment(), HistoryView {
    private var progressSwipe: Boolean = false

    private lateinit var presenter: HistoryPresenter
    private lateinit var adapter: HistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        tv_history_nodata.gone()

        adapter = HistoryAdapter(context!!)
        rv_history.adapter = adapter
        rv_history.layoutManager = LinearLayoutManager(context)

        presenter = HistoryPresenter(this, ApiRepository(), Gson())
        presenter.getHistoryData(context?.sharePref()?.getString(SharedPreferenceKey.API_KEY, "")!!)

        sr_history.setOnRefreshListener {
            progressSwipe = true
            presenter.getHistoryData(
                context?.sharePref()?.getString(
                    SharedPreferenceKey.API_KEY,
                    ""
                )!!
            )
        }
    }

    override fun showLoading() {
        if (!progressSwipe)
            pb_history.visible()
    }

    override fun hideLoading() {
        if (!progressSwipe)
            pb_history.invisible()
    }

    override fun showHistory(data: List<History>, total: Int) {
        sr_history.isRefreshing = false

        if (total > 0) {
            tv_history_nodata.gone()
            rv_history.visible()
            val newList = mutableListOf<Any>()

            for (item in data) {
                when (item.type) {
                    "topup" -> {
                        val topUp = TopUp(item.balance, item.time)
                        newList.add(topUp)
                    }
                    "refill" -> {
                        val refill =
                            Refill(item.disPlace, item.disFloor, item.size, item.balance, item.time)
                        newList.add(refill)
                    }
                }
            }

            adapter.addList(newList)
        } else {
            tv_history_nodata.visible()
            rv_history.invisible()
        }
    }
}
