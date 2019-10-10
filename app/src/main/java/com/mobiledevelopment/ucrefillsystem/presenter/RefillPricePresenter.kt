package com.mobiledevelopment.ucrefillsystem.presenter

import com.google.gson.Gson
import com.mobiledevelopment.ucrefillsystem.helper.CoroutineContextProvider
import com.mobiledevelopment.ucrefillsystem.model.Dispenser
import com.mobiledevelopment.ucrefillsystem.model.RefillPriceResponse
import com.mobiledevelopment.ucrefillsystem.network.ApiRepository
import com.mobiledevelopment.ucrefillsystem.network.RefillWaterAPI
import com.mobiledevelopment.ucrefillsystem.viewinterface.RefillPriceView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RefillPricePresenter(
    private val view: RefillPriceView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getPaymentData(code: Int) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val dispenser = gson.fromJson(
                apiRepository.doRequest(RefillWaterAPI.getDispenserInfo(code)).await(),
                Dispenser::class.java
            )

            if (dispenser.code == 1) {
                view.hideLoading()
                view.showDispenser(dispenser)
            } else if (dispenser.code == 0) {
                view.loadDataFailed()
            }
        }

        GlobalScope.launch(context.main) {
            val refillprices = gson.fromJson(
                apiRepository.doRequest(RefillWaterAPI.getRefillPrices()).await(),
                RefillPriceResponse::class.java
            )

            view.showPrices(refillprices.prices)
        }
    }
}