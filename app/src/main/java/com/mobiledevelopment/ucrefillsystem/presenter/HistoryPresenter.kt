package com.mobiledevelopment.ucrefillsystem.presenter

import com.google.gson.Gson
import com.mobiledevelopment.ucrefillsystem.model.response.HistoriesResponse
import com.mobiledevelopment.ucrefillsystem.model.response.UsersResponse
import com.mobiledevelopment.ucrefillsystem.network.ApiRepository
import com.mobiledevelopment.ucrefillsystem.network.RefillWaterAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HistoryPresenter (private val view : MainView,
                        private val apiRepository: ApiRepository,
                        private val gson: Gson
){
    fun getHistoryList (name : String?){
        view.showLoading()

        GlobalScope.launch(Dispatchers.Main){
            val data = gson.fromJson(apiRepository.doRequest(RefillWaterAPI.SeeHistory(name)).await(), HistoriesResponse::class.java)

            view.showHistoryList(data.histories)
            view.hideLoading()
        }}}