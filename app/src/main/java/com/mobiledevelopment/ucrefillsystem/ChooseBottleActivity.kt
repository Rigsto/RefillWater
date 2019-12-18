package com.mobiledevelopment.ucrefillsystem

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.mobiledevelopment.ucrefillsystem.adapter.choosebottle.DefaultBottleAdapter
import com.mobiledevelopment.ucrefillsystem.adapter.choosebottle.MyBottleAdapter
import com.mobiledevelopment.ucrefillsystem.helper.*
import com.mobiledevelopment.ucrefillsystem.model.Bottle
import com.mobiledevelopment.ucrefillsystem.network.ApiRepository
import com.mobiledevelopment.ucrefillsystem.presenter.BottlePresenter
import com.mobiledevelopment.ucrefillsystem.presenter.MessagePresenter
import com.mobiledevelopment.ucrefillsystem.viewinterface.BottleView
import com.mobiledevelopment.ucrefillsystem.viewinterface.MessageView
import kotlinx.android.synthetic.main.activity_choose_bottle.*

class ChooseBottleActivity : AppCompatActivity(), BottleView, MessageView {
    private lateinit var myBottleAdapter: MyBottleAdapter
    private lateinit var defaultBottleAdapter: DefaultBottleAdapter
    private lateinit var bottlePresenter: BottlePresenter
    private lateinit var messagePresenter: MessagePresenter

    private var idBottle = ""
    private var idGallon = ""
    private var price = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_bottle)

        idGallon = intent?.getStringExtra("idGallon")!!
        tv_choose_place.text = "Lantai ${idGallon.getFloor()}"
        tv_choose_name.text = sharePref().getString(SharedPreferenceKey.NAME_KEY, "")
        tv_choose_point.text = sharePref().getInt(SharedPreferenceKey.MONEY_KEY, 0).readableNumber()

        messagePresenter = MessagePresenter(this, ApiRepository(), Gson())
        bottlePresenter = BottlePresenter(this, ApiRepository(), Gson())
        bottlePresenter.getAllBottles(sharePref().getString(SharedPreferenceKey.ACCESS_TOKEN, "")!!)

        defaultBottleAdapter = DefaultBottleAdapter(this) {
            idBottle = it.id!!
            tv_choose_size.text = "${it.capacity} ml"
            tv_choose_spend.text = it.price.readableNumber()
            price = it.price
        }
        rv_default_bottle.adapter = defaultBottleAdapter
        rv_default_bottle.setHasFixedSize(true)
        rv_default_bottle.layoutManager = GridLayoutManager(this, 3)

        myBottleAdapter = MyBottleAdapter(this) {
            idBottle = it.id!!
            tv_choose_size.text = "${it.capacity} ml"
            tv_choose_spend.text = it.price.readableNumber()
            price = it.price
        }
        rv_my_bottle.adapter = myBottleAdapter
        rv_my_bottle.setHasFixedSize(true)
        rv_my_bottle.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        supportActionBar?.hide()

        btn_choose_fill.setOnClickListener {
            if (idBottle == "") {
                Toast.makeText(this, "Please pick a size", Toast.LENGTH_SHORT).show()
            } else {
                messagePresenter.refill(
                    sharePref().getString(
                        SharedPreferenceKey.ACCESS_TOKEN,
                        ""
                    )!!, idGallon, idBottle
                )
            }
        }
    }

    override fun showLoading() {
        pb_default_bottle.visible()
        pb_my_bottle.visible()
        rv_default_bottle.invisible()
        rv_my_bottle.invisible()
    }

    override fun hideLoading() {
        pb_default_bottle.gone()
        pb_my_bottle.gone()
        rv_default_bottle.visible()
        rv_my_bottle.visible()
    }

    override fun showBottles(default: List<Bottle>, my: List<Bottle>) {
        defaultBottleAdapter.addList(default)
        myBottleAdapter.addList(my)
    }

    override fun showMessageLoading() {
        btn_choose_fill.invisible()
        pb_fill_my_bottle.visible()
    }

    override fun hideMessageLoading() {
        btn_choose_fill.visible()
        pb_fill_my_bottle.gone()
    }

    override fun getMessage(message: String) {
        if (message == "Enjoy, keep hydrated") {
            startActivity(
                Intent(this, RefillPaymentResultActivity::class.java).putExtra(
                    "price",
                    price
                )
            )
        } else {
            hideMessageLoading()
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }
}
