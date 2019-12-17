package com.mobiledevelopment.ucrefillsystem.presenter

import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.google.gson.Gson
import com.mobiledevelopment.ucrefillsystem.helper.CoroutineContextProvider
import com.mobiledevelopment.ucrefillsystem.model.History
import com.mobiledevelopment.ucrefillsystem.network.ApiRepository
import com.mobiledevelopment.ucrefillsystem.network.RefillWaterAPI
import com.mobiledevelopment.ucrefillsystem.viewinterface.HistoryView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject

class HistoryPresenter(
    private val view: HistoryView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun history(name: String):String {
        view.showLoading()
        AndroidNetworking.get("https://refill.fusionsvisual.com/api/my-topUp")
            .setPriority(Priority.LOW)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {

                    println(response)




                }

                override fun onError(anError: ANError?) {
                    view.hideLoading()
                    println("error here")
                    Log.d("ONERROR",anError?.errorDetail?.toString())

                }
            })
            
            view.hideLoading()
        return "ok"
        }
    }
