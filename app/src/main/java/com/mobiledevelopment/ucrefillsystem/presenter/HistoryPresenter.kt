package com.mobiledevelopment.ucrefillsystem.presenter

import com.google.gson.Gson
import com.mobiledevelopment.ucrefillsystem.helper.CoroutineContextProvider
import com.mobiledevelopment.ucrefillsystem.model.response.HistoriesResponse
import com.mobiledevelopment.ucrefillsystem.network.ApiRepository
import com.mobiledevelopment.ucrefillsystem.network.RefillWaterAPI
import com.mobiledevelopment.ucrefillsystem.viewinterface.HistoryView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HistoryPresenter(
    private val view: HistoryView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getHistoryList(name: String) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequest(RefillWaterAPI.SeeHistory(name)).await(),
                HistoriesResponse::class.java
            )

            view.showHistoryList(data.histories)
            view.hideLoading()
        }
    }
}