package com.mobiledevelopment.ucrefillsystem.fragment.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.mobiledevelopment.ucrefillsystem.R
import com.mobiledevelopment.ucrefillsystem.RefillActivity
import com.mobiledevelopment.ucrefillsystem.adapter.AvailableAdapter
import com.mobiledevelopment.ucrefillsystem.helper.*
import com.mobiledevelopment.ucrefillsystem.model.Dispenser
import com.mobiledevelopment.ucrefillsystem.network.ApiRepository
import com.mobiledevelopment.ucrefillsystem.presenter.AvailablePresenter
import com.mobiledevelopment.ucrefillsystem.viewinterface.AvailableView
import kotlinx.android.synthetic.main.content_fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), View.OnClickListener, AvailableView {
    private var dispenserList: List<Any> = mutableListOf()

    private lateinit var adapter: AvailableAdapter
    private lateinit var presenter: AvailablePresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        ll_topup.setOnClickListener(this)
        ll_transfer.setOnClickListener(this)
        ll_withdraw.setOnClickListener(this)
        ll_points.setOnClickListener(this)
        fab_scan.setOnClickListener(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        loadMoney()

        adapter = AvailableAdapter(context!!)
        rv_available.adapter = adapter
        rv_available.layoutManager = LinearLayoutManager(context)

        presenter = AvailablePresenter(this, ApiRepository(), Gson())
        presenter.getAvailableDispenser()
    }

    override fun showLoading() {
        pb_available.visible()
    }

    override fun hideLoading() {
        pb_available.invisible()
    }

    override fun showAvailableList(data: List<Dispenser>) {
        dispenserList = convertData(data)
        adapter.addList(dispenserList)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.ll_topup -> Toast.makeText(context, "Coming Soon", Toast.LENGTH_SHORT).show()
            R.id.ll_transfer -> Toast.makeText(context, "Coming Soon", Toast.LENGTH_SHORT).show()
            R.id.ll_withdraw -> Toast.makeText(context, "Coming Soon", Toast.LENGTH_SHORT).show()
            R.id.ll_points -> Toast.makeText(context, "Coming Soon", Toast.LENGTH_SHORT).show()
            R.id.fab_scan -> {
                startActivity(Intent(context, RefillActivity::class.java))
            }
        }
    }

    private fun loadMoney() {
        val sp = context?.sharePref()
        val money = sp?.getInt(SharedPreferenceKey.MONEY_KEY, 0)

        tv_money.text = "Rp. ${money?.readableNumber()}"
    }

    private fun convertData(data: List<Dispenser>): List<Any> {
        val available = mutableListOf<Dispenser>()
        val notAvailable = mutableListOf<Dispenser>()

        for (dis in data) {
            if (dis.remain <= 2) {
                notAvailable.add(dis)
            } else {
                available.add(dis)
            }
        }

        val any = mutableListOf<Any>()
        any.add("Available")
        any.addAll(available)
        any.add("Not Available")
        any.addAll(notAvailable)

        return any
    }
}
