package com.mobiledevelopment.ucrefillsystem

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import kotlinx.android.synthetic.main.activity_scan_refill.*

class ScanRefillActivity : AppCompatActivity() {
    private lateinit var codeScanner: CodeScanner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_refill)

        codeScanner = CodeScanner(this, sqr_refill)
        codeScanner.decodeCallback = DecodeCallback {
            runOnUiThread {
                val data = it.text
                if (checkScan(data)) {
                    startActivity(
                        Intent(this, PayRefillActivity::class.java).putExtra(
                            "code",
                            data.toInt()
                        )
                    )
                } else {
//                    alertOk("Scan QR", "Please try again", "Try Again") {
//                        Toast.makeText(this, "berhasil", Toast.LENGTH_SHORT).show()
//                    }

                    val builder = AlertDialog.Builder(this)

                    builder.setTitle("Scan QR")
                    builder.setMessage("Please try again")
                    builder.setPositiveButton("Try Again") { dialog, which ->
                        codeScanner.startPreview()
                        dialog.dismiss()
                    }
                    builder.setOnDismissListener {
                        codeScanner.startPreview()
                    }
                    builder.show()
                }
            }
        }

        codeScanner.errorCallback = ErrorCallback {
            runOnUiThread {
                Toast.makeText(
                    this,
                    "Camera initialization error: ${it.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        codeScanner.startPreview()

        supportActionBar?.hide()
        tool_bar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        tool_bar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }

    private fun checkScan(data: String): Boolean {
        try {
            val temp = data.toInt()
            return temp in 100..1299
        } catch (e: NumberFormatException) {
            return false
        }
    }
}
