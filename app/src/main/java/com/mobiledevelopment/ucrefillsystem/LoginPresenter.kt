package com.mobiledevelopment.ucrefillsystem

import com.google.gson.Gson
import com.mobiledevelopment.ucrefillsystem.model.UserResponse
import com.mobiledevelopment.ucrefillsystem.network.ApiRepository
import com.mobiledevelopment.ucrefillsystem.network.RefillWaterAPI
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class LoginPresenter (private val view : LoginActivity.LoginView,
                      private val apiRepository: ApiRepository,
                      private val gson: Gson
){
    fun getUserList (name : String){
        view.showLoading()

        GlobalScope.launch(Dispatcher){
            val data = gson.fromJson(apiRepository.doRequest(RefillWaterAPI.getUser(name)).await(), UserResponse::class.java)
        }

        view.showNameList()
        view.hideLoading()

    }
}