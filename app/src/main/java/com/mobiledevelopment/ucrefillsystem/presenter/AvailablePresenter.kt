package com.mobiledevelopment.ucrefillsystem.presenter

import com.google.gson.Gson
import com.mobiledevelopment.ucrefillsystem.helper.CoroutineContextProvider
import com.mobiledevelopment.ucrefillsystem.model.DispenserResponse
import com.mobiledevelopment.ucrefillsystem.network.ApiRepository
import com.mobiledevelopment.ucrefillsystem.network.RefillWaterAPI
import com.mobiledevelopment.ucrefillsystem.viewinterface.AvailableView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AvailablePresenter(private val view: AvailableView,
                         private val apiRepository: ApiRepository,
                         private val gson: Gson,
                         private val context: CoroutineContextProvider = CoroutineContextProvider()){

    fun getAvailableDispenser(){
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequest(RefillWaterAPI.getAvailableDispenser()).await(),
                DispenserResponse::class.java
            )

            view.hideLoading()
            view.showAvailableList(data.dispensers?: mutableListOf())
        }
    }
}