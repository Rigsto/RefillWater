package com.mobiledevelopment.ucrefillsystem.presenter

import com.google.gson.Gson
import com.mobiledevelopment.ucrefillsystem.helper.CoroutineContextProvider
import com.mobiledevelopment.ucrefillsystem.model.LoginResponse
import com.mobiledevelopment.ucrefillsystem.network.ApiRepository
import com.mobiledevelopment.ucrefillsystem.network.RefillWaterAPI
import com.mobiledevelopment.ucrefillsystem.viewinterface.LoginView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginPresenter(
    private val view: LoginView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun login(email: String, password: String) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequest(
                    RefillWaterAPI.login(
                        email,
                        password
                    )
                ).await(), LoginResponse::class.java
            )

            if (data.code == 0) {
                view.loginFailed()
                view.hideLoading()
            } else {
                view.loginSuccess(data)
            }
        }
    }
}