package com.mobiledevelopment.ucrefillsystem.presenter

import com.google.gson.Gson
import com.mobiledevelopment.ucrefillsystem.helper.CoroutineContextProvider
import com.mobiledevelopment.ucrefillsystem.model.User
import com.mobiledevelopment.ucrefillsystem.network.ApiRepository
import com.mobiledevelopment.ucrefillsystem.network.RefillWaterAPI
import com.mobiledevelopment.ucrefillsystem.viewinterface.LoginView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginPresenter(
    private val view: LoginView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun login(username: String, password: String) {
        view.showLoading()

//        GlobalScope.launch(Dispatchers.Main) {
//            val data = gson.fromJson(
//                apiRepository.doRequest(RefillWaterAPI.getUser(name)).await(),
//                UsersResponse::class.java
//            )
//
//            view.getUser(data.users.get(0))
//            view.hideLoading()
//        }

        GlobalScope.launch(context.main) {
            view.showLoading()
            delay(2000L)

            view.loginSuccess(
                User(
                    "Auriga",
                    "aaristo01@student.ciputra.ac.id",
                    "L",
                    "IMT",
                    200,
                    1,
                    "20417012",
                    "abc"
                )
            )
            view.hideLoading()
        }

    }

}