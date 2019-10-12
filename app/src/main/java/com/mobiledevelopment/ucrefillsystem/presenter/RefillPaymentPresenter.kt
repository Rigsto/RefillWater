package com.mobiledevelopment.ucrefillsystem.presenter

import com.google.gson.Gson
import com.mobiledevelopment.ucrefillsystem.helper.CoroutineContextProvider
import com.mobiledevelopment.ucrefillsystem.model.response.CodeResponse
import com.mobiledevelopment.ucrefillsystem.network.ApiRepository
import com.mobiledevelopment.ucrefillsystem.network.RefillWaterAPI
import com.mobiledevelopment.ucrefillsystem.viewinterface.PaymentView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RefillPaymentPresenter(
    private val view: PaymentView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun refillPayment(api: String, idDispenser: Int, priceCode: Int) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val code = gson.fromJson(
                apiRepository.doRequest(
                    RefillWaterAPI.payRefill(
                        api,
                        idDispenser,
                        priceCode
                    )
                ).await(), CodeResponse::class.java
            )

            view.hideLoading()
            view.loadData(code.code)
        }
    }
}