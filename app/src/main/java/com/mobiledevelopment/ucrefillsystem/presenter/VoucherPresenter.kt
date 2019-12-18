package com.mobiledevelopment.ucrefillsystem.presenter

import com.google.gson.Gson
import com.mobiledevelopment.ucrefillsystem.helper.CoroutineContextProvider
import com.mobiledevelopment.ucrefillsystem.model.response.VoucherResponse
import com.mobiledevelopment.ucrefillsystem.network.ApiRepository
import com.mobiledevelopment.ucrefillsystem.network.RefillWaterAPI
import com.mobiledevelopment.ucrefillsystem.viewinterface.VoucherView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class VoucherPresenter(
    private val view: VoucherView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getAllVoucher() {
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequest(RefillWaterAPI.getAds()).await(),
                VoucherResponse::class.java
            )

            val vouchers = data.data.toList()
            view.showVouchers(vouchers)
        }
    }
}