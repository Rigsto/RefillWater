package com.mobiledevelopment.ucrefillsystem.presenter

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.mobiledevelopment.ucrefillsystem.helper.CoroutineContextProvider
import com.mobiledevelopment.ucrefillsystem.model.Bottle
import com.mobiledevelopment.ucrefillsystem.model.BottleResponse
import com.mobiledevelopment.ucrefillsystem.network.ApiRepository
import com.mobiledevelopment.ucrefillsystem.network.RefillWaterAPI
import com.mobiledevelopment.ucrefillsystem.viewinterface.BottleView
import org.json.JSONObject

class BottlePresenter(
    private val view: BottleView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getAllBottles(accToken: String) {
        view.showLoading()

        AndroidNetworking.get(RefillWaterAPI.getBottle())
            .addHeaders("Accept", "application/json")
            .addHeaders("Authorization", "Bearer $accToken")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    val bottleResponse = gson.fromJson(
                        gson.fromJson(response.toString(), JsonElement::class.java),
                        BottleResponse::class.java
                    )
                    val data = bottleResponse.data

                    val my = mutableListOf<Bottle>()
                    val default = mutableListOf<Bottle>()
                    for (bot in data) {
                        if (bot.owner == "Dummy") {
                            default.add(bot)
                        } else {
                            my.add(bot)
                        }
                    }

                    view.hideLoading()
                    view.showBottles(default, my)
                }

                override fun onError(anError: ANError) {

                }
            })
    }
}