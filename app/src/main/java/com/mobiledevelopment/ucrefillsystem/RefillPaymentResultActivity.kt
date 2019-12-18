package com.mobiledevelopment.ucrefillsystem

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mobiledevelopment.ucrefillsystem.helper.SharedPreferenceKey
import com.mobiledevelopment.ucrefillsystem.helper.readableNumber
import com.mobiledevelopment.ucrefillsystem.helper.sharePref
import kotlinx.android.synthetic.main.activity_refill_payment_result.*

class RefillPaymentResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_refill_payment_result)

        val price = intent.getIntExtra("price", 0)
        val bal = sharePref().getInt(SharedPreferenceKey.MONEY_KEY, 0)

        tv_payment_ststus_money.text = price.readableNumber()

        sharePref().edit().putInt(SharedPreferenceKey.MONEY_KEY, bal - price).apply()

        supportActionBar?.hide()
        tool_bar.setNavigationIcon(R.drawable.ic_clear_white_24dp)
        tool_bar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}
