package com.mobiledevelopment.ucrefillsystem

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.budiyev.android.codescanner.*
import com.google.zxing.BarcodeFormat
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.mobiledevelopment.ucrefillsystem.helper.checkGallonId
import kotlinx.android.synthetic.main.activity_scan.*

class ScanActivity : AppCompatActivity() {
    private lateinit var codeScanner: CodeScanner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)

        checkPermission()

        codeScanner = CodeScanner(this, sqr_refill)
        codeScanner.camera = CodeScanner.CAMERA_BACK
        codeScanner.formats = listOf(BarcodeFormat.QR_CODE)
        codeScanner.autoFocusMode = AutoFocusMode.SAFE
        codeScanner.scanMode = ScanMode.SINGLE
        codeScanner.isAutoFocusEnabled = true
        codeScanner.isFlashEnabled = false
        codeScanner.isTouchFocusEnabled = true
        codeScanner.decodeCallback = DecodeCallback {
            runOnUiThread {
                val data = it.text
                if (data.checkGallonId()) {
                    startActivity(
                        Intent(
                            this,
                            ChooseBottleActivity::class.java
                        ).putExtra("idGallon", data)
                    )
                } else {
                    val builder = AlertDialog.Builder(this)

                    builder.setTitle("Scan QR")
                    builder.setMessage("Please try again")
                    builder.setPositiveButton("Try Again") { dialog, _ ->
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

            }
        }

        codeScanner.startPreview()

        supportActionBar?.hide()
        tool_bar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        tool_bar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun checkPermission() {
        Dexter.withActivity(this)
            .withPermission(Manifest.permission.CAMERA)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response: PermissionGrantedResponse) {
                }

                override fun onPermissionDenied(response: PermissionDeniedResponse) {
                    if (response.isPermanentlyDenied) {
                        Toast.makeText(
                            this@ScanActivity,
                            "Please allow the camera permission",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permission: PermissionRequest?,
                    token: PermissionToken
                ) {
                    token.continuePermissionRequest()
                }
            }).check()
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }
}
