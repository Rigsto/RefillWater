package com.mobiledevelopment.ucrefillsystem

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.mobiledevelopment.ucrefillsystem.adapter.RefillPriceAdapter
import com.mobiledevelopment.ucrefillsystem.helper.*
import com.mobiledevelopment.ucrefillsystem.model.Dispenser
import com.mobiledevelopment.ucrefillsystem.model.RefillPrice
import com.mobiledevelopment.ucrefillsystem.network.ApiRepository
import com.mobiledevelopment.ucrefillsystem.presenter.RefillPricePresenter
import com.mobiledevelopment.ucrefillsystem.viewinterface.RefillPriceView
import kotlinx.android.synthetic.main.activity_pay_refill.*

class PayRefillActivity : AppCompatActivity(), RefillPriceView, View.OnClickListener {
    private var code: Int = 0
    private var currentAmount: Int = -1
    private var refill_id: Int = 1
    private var checkRadio = false

    private lateinit var adapter: RefillPriceAdapter
    private lateinit var presenter: RefillPricePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay_refill)

        title = resources.getString(R.string.payment_details)

        btn_payment_pay.setOnClickListener(this)
        btn_payment_cancel.setOnClickListener(this)

        code = intent.getIntExtra("code", 0)

        adapter = RefillPriceAdapter(this) {
            tv_payment_total.text = "Rp. ${it.price.readableNumber()}"
            currentAmount = it.price
            refill_id = it.id
            checkRadio = true
        }
        rv_payment_size.adapter = adapter

        val gridLayout = GridLayoutManager(this, 3)
        rv_payment_size.layoutManager = gridLayout

        presenter = RefillPricePresenter(this, ApiRepository(), Gson())
        presenter.getPaymentData(code)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_payment_pay -> {
                if (enoughMoney() && checkRadio) {
                    startActivity(
                        Intent(
                            this,
                            RefillPaymentResultActivity::class.java
                        ).putExtra("code", code).putExtra("refill_id", refill_id)
                    )
                } else {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Cancel Payment")
                    builder.setMessage("insufficient balance. Please top up your wallet!")
                    builder.setPositiveButton("OK") { dialog, which ->
                        dialog.dismiss()
                        startActivity(Intent(this, HomeActivity::class.java))
                        finish()
                    }
                    builder.setOnDismissListener {
                        startActivity(Intent(this, HomeActivity::class.java))
                        finish()
                    }
                    builder.show()
                }
            }
            R.id.btn_payment_cancel -> {
                onBackPressed()
            }
        }
    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Cancel Payment")
        builder.setMessage("Are you sure?")
        builder.setPositiveButton("Yes") { dialog, which ->
            dialog.dismiss()
            finish()
        }
        builder.setNegativeButton("No") { dialog, which ->
            dialog.dismiss()
        }
        builder.show()
    }

    override fun showLoading() {
        pb_refillprice.visible()
        ll_refillprice_btn.gone()
        rl_refillprice_amount.invisible()
        rg_refillprice_size.invisible()
        tv_payment_floor.invisible()
        tv_payment_place.invisible()
    }

    override fun hideLoading() {
        pb_refillprice.gone()
        ll_refillprice_btn.visible()
        rl_refillprice_amount.visible()
        rg_refillprice_size.visible()
        tv_payment_floor.visible()
        tv_payment_place.visible()
    }

    override fun showDispenser(dispenser: Dispenser) {
        tv_payment_floor.text = dispenser.floor
        tv_payment_place.text = dispenser.place
    }

    override fun showPrices(prices: List<RefillPrice>) {
        adapter.addList(prices)
    }

    override fun loadDataFailed() {
        val builder = AlertDialog.Builder(this)

        builder.setMessage("Wrong Dispenser Code")
        builder.setPositiveButton("Try Again") { dialog, which ->
            dialog.dismiss()
            onBackPressed()
            finish()
        }
        builder.setNegativeButton("Cancel") { dialog, which ->
            dialog.dismiss()
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
        builder.setOnDismissListener {
            onBackPressed()
            finish()
        }
        builder.show()
    }

    private fun enoughMoney(): Boolean {
        val wallet = sharePref().getInt(SharedPreferenceKey.MONEY_KEY, 0)
        return wallet > currentAmount
    }
}
