package com.mobiledevelopment.ucrefillsystem.presenter

import com.google.gson.Gson
import com.mobiledevelopment.ucrefillsystem.helper.CoroutineContextProvider
import com.mobiledevelopment.ucrefillsystem.model.response.MoneyResponse
import com.mobiledevelopment.ucrefillsystem.network.ApiRepository
import com.mobiledevelopment.ucrefillsystem.network.RefillWaterAPI
import com.mobiledevelopment.ucrefillsystem.viewinterface.WalletView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class WalletPresenter(
    private val view: WalletView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun reloadWallet(api: String) {
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequest(RefillWaterAPI.loadWallet(api)).await(),
                MoneyResponse::class.java
            )

            view.showWallet(data.money)
        }
    }
}