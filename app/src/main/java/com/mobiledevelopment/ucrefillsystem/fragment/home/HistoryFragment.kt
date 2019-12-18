package com.mobiledevelopment.ucrefillsystem.fragment.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.mobiledevelopment.ucrefillsystem.R
import com.mobiledevelopment.ucrefillsystem.adapter.history.HistoryAdapter
import com.mobiledevelopment.ucrefillsystem.helper.SharedPreferenceKey
import com.mobiledevelopment.ucrefillsystem.helper.sharePref
import com.mobiledevelopment.ucrefillsystem.model.History
import com.mobiledevelopment.ucrefillsystem.network.ApiRepository
import com.mobiledevelopment.ucrefillsystem.presenter.HistoryPresenter
import com.mobiledevelopment.ucrefillsystem.viewinterface.HistoryView
import kotlinx.android.synthetic.main.fragment_history.*

/**
 * A simple [Fragment] subclass.
 */
class HistoryFragment : Fragment(), HistoryView{
    private lateinit var historyAdapter: HistoryAdapter
    private lateinit var presenter: HistoryPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = HistoryPresenter(this, ApiRepository(), Gson())
        presenter.getHistory(
            context!!.sharePref().getString(
                SharedPreferenceKey.ACCESS_TOKEN,
                ""
            )!!
        )

    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showHistoryList(list: List<History>) {
        historyAdapter = HistoryAdapter(context!!, list)
        rv_history.adapter = historyAdapter
        rv_history.setHasFixedSize(true)
        rv_history.layoutManager =
            LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)
    }
}
