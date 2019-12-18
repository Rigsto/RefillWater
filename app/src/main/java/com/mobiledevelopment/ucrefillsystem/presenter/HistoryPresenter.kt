package com.mobiledevelopment.ucrefillsystem.presenter

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.mobiledevelopment.ucrefillsystem.helper.CoroutineContextProvider
import com.mobiledevelopment.ucrefillsystem.model.response.HistoryResponse
import com.mobiledevelopment.ucrefillsystem.network.ApiRepository
import com.mobiledevelopment.ucrefillsystem.network.RefillWaterAPI
import com.mobiledevelopment.ucrefillsystem.viewinterface.HistoryView
import org.json.JSONObject

class HistoryPresenter(
    private val view: HistoryView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getHistory(accToken: String) {
        view.showLoading()

        AndroidNetworking.get(RefillWaterAPI.getHistory())
            .addHeaders("Accept", "application/json")
            .addHeaders("Authorization", "Bearer $accToken")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    val historyResponse = gson.fromJson(
                        gson.fromJson(response.toString(), JsonElement::class.java),
                        HistoryResponse::class.java
                    )
                    val data = historyResponse.data

                    view.hideLoading()
                    view.showHistoryList(data)
                }

                override fun onError(anError: ANError) {

                }
            })
    }
}

