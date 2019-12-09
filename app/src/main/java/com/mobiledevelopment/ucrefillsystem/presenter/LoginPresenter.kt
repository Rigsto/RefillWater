package com.mobiledevelopment.ucrefillsystem.presenter

import com.google.gson.Gson
import com.mobiledevelopment.ucrefillsystem.helper.CoroutineContextProvider
import com.mobiledevelopment.ucrefillsystem.model.User
import com.mobiledevelopment.ucrefillsystem.network.ApiRepository
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

    fun SendUser(name: String?) {
//        AndroidNetworking.post(RefillWaterAPI.CreateUser(name))
//            .addBodyParameter("nim",txNim.text.toString())
//            .addBodyParameter("name",txName.text.toString())
//            .addBodyParameter("address",txAddress.text.toString())
//            .addBodyParameter("gender",gender)
//            .setPriority(Priority.MEDIUM)
//            .build()
//            .getAsJSONObject(object : JSONObjectRequestListener {
//                override fun onResponse(response: JSONObject?) {
//                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                }
//
//                override fun onError(anError: ANError?) {
//                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                }
//
//            })
    }
}