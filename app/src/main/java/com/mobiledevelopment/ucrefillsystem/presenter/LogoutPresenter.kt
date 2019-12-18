package com.mobiledevelopment.ucrefillsystem.presenter

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.google.gson.Gson
import com.mobiledevelopment.ucrefillsystem.helper.CoroutineContextProvider
import com.mobiledevelopment.ucrefillsystem.network.ApiRepository
import com.mobiledevelopment.ucrefillsystem.network.RefillWaterAPI
import com.mobiledevelopment.ucrefillsystem.viewinterface.LogoutView
import org.json.JSONObject

class LogoutPresenter(
    private val view: LogoutView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun logout(accToken: String) {
        view.showLoading()

        AndroidNetworking.post(RefillWaterAPI.logout())
            .addHeaders("Accept", "application/json")
            .addHeaders("Authorization", "Bearer $accToken")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    val message = response.getString("message")

                    if (message == "Logged out") {
                        view.logoutSuccess()
                    } else {
                        view.hideLoading()
                        view.logoutFailed(message)
                    }
                }

                override fun onError(anError: ANError) {

                }
            })
    }
}