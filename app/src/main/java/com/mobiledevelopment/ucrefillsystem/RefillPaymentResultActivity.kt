package com.mobiledevelopment.ucrefillsystem

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.mobiledevelopment.ucrefillsystem.helper.*
import com.mobiledevelopment.ucrefillsystem.model.Dispenser
import com.mobiledevelopment.ucrefillsystem.model.RefillPrice
import com.mobiledevelopment.ucrefillsystem.network.ApiRepository
import com.mobiledevelopment.ucrefillsystem.presenter.RefillPaymentPresenter
import com.mobiledevelopment.ucrefillsystem.viewinterface.PaymentView
import kotlinx.android.synthetic.main.activity_refill_payment_result.*

class RefillPaymentResultActivity : AppCompatActivity(), PaymentView {
    private lateinit var dispenser: Dispenser
    private lateinit var refillPrice: RefillPrice
    private lateinit var presenter: RefillPaymentPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_refill_payment_result)

        dispenser = intent.getParcelableExtra("dispenser")
        refillPrice = intent.getParcelableExtra("refill")

        supportActionBar?.hide()
        tool_bar.setNavigationIcon(R.drawable.ic_clear_white_24dp)
        tool_bar.setNavigationOnClickListener {
            onBackPressed()
        }

        presenter = RefillPaymentPresenter(this, ApiRepository(), Gson())
        presenter.refillPayment(
            sharePref()?.getString(SharedPreferenceKey.API_KEY, "")!!,
            dispenser.code,
            refillPrice.id
        )
    }

    override fun onBackPressed() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    override fun showLoading() {
        rl_payment_status.setBackgroundColor(Color.WHITE)
        linearLayout.invisible()
        pb_payment_status.visible()
    }

    override fun hideLoading() {
        linearLayout.visible()
        pb_payment_status.gone()
    }

    override fun loadData(status: Int) {
        if (status == 1) { //successful
            rl_payment_status.setBackgroundColor(ContextCompat.getColor(this, R.color.b4_success))
            Glide.with(this).load(R.drawable.ic_check_white_24dp).into(img_payment_status_logo)
            tv_payment_status.text = "Payment Successful"
            tv_payment_status2.text = "You have paid"
            tv_payment_ststus_money.text = "Rp. ${refillPrice.price}"
            tv_payment_status_floor.text = dispenser.floor
            tv_payment_status_place.text = dispenser.place
            tv_payment_status_ml.text = "${refillPrice.size} ml"

        } else { //failed
            rl_payment_status.setBackgroundColor(ContextCompat.getColor(this, R.color.b4_danger))
            Glide.with(this).load(R.drawable.ic_clear_white_24dp).into(img_payment_status_logo)
            tv_payment_status.text = "Payment Failed"
            tv_payment_status2.text = "We have refunded"
            tv_payment_ststus_money.text = "Rp. ${refillPrice.price}"
            tv_payment_status_floor.text = dispenser.floor
            tv_payment_status_place.text = dispenser.place
            tv_payment_status_ml.text = "${refillPrice.size} ml"
        }
    }
}
