package com.mobiledevelopment.ucrefillsystem.presenter

import com.google.gson.Gson
import com.mobiledevelopment.ucrefillsystem.helper.CoroutineContextProvider
import com.mobiledevelopment.ucrefillsystem.model.response.CodeResponse
import com.mobiledevelopment.ucrefillsystem.network.ApiRepository
import com.mobiledevelopment.ucrefillsystem.viewinterface.CodeView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FillMyBottlePresenter(
    private val view: CodeView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun fillMyBottle() {
        GlobalScope.launch(context.main) {
            view.showLoading()
            delay(2000L)

            view.dataSuccess(CodeResponse(1))
            view.hideLoading()
        }
    }
}