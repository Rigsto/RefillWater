package com.mobiledevelopment.ucrefillsystem.presenter

import com.google.gson.Gson
import com.mobiledevelopment.ucrefillsystem.helper.CoroutineContextProvider
import com.mobiledevelopment.ucrefillsystem.model.response.GallonsResponse
import com.mobiledevelopment.ucrefillsystem.network.ApiRepository
import com.mobiledevelopment.ucrefillsystem.network.RefillWaterAPI
import com.mobiledevelopment.ucrefillsystem.viewinterface.GallonVolumeView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GallonVolumePresenter(
    private val view: GallonVolumeView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getGallons() {
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequest(RefillWaterAPI.getGallon()).await(),
                GallonsResponse::class.java
            )

            view.getGallons(data.data.toList())
        }
    }
}