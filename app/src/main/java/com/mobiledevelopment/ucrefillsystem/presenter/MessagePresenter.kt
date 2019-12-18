package com.mobiledevelopment.ucrefillsystem.presenter

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.google.gson.Gson
import com.mobiledevelopment.ucrefillsystem.helper.CoroutineContextProvider
import com.mobiledevelopment.ucrefillsystem.network.ApiRepository
import com.mobiledevelopment.ucrefillsystem.network.RefillWaterAPI
import com.mobiledevelopment.ucrefillsystem.viewinterface.MessageView
import org.json.JSONObject

class MessagePresenter(
    private val view: MessageView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun refill(accToken: String, idGallon: String, idBottle: String) {
        view.showMessageLoading()

        AndroidNetworking.post(RefillWaterAPI.refill())
            .addHeaders("Accept", "application/json")
            .addHeaders("Authorization", "Bearer $accToken")
            .addBodyParameter("bottle_id", idBottle)
            .addBodyParameter("gallon_id", idGallon)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    val message = response.getString("message")

                    view.getMessage(message)
                }

                override fun onError(anError: ANError) {

                }
            })
    }
}