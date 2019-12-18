package com.mobiledevelopment.ucrefillsystem.presenter

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.mobiledevelopment.ucrefillsystem.helper.CoroutineContextProvider
import com.mobiledevelopment.ucrefillsystem.model.User
import com.mobiledevelopment.ucrefillsystem.network.ApiRepository
import com.mobiledevelopment.ucrefillsystem.network.RefillWaterAPI
import com.mobiledevelopment.ucrefillsystem.viewinterface.LoginView
import org.json.JSONException
import org.json.JSONObject


class LoginPresenter(
    private val view: LoginView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun login(email: String, password: String) {
        view.showLoading()

        AndroidNetworking.post(RefillWaterAPI.login())
            .addHeaders("Accept", "application/json")
            .addBodyParameter("email", email)
            .addBodyParameter("password", password)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    var message: String? = null
                    var accessToken = ""
                    var refreshToken = ""

                    try {
                        accessToken = response.getString("access_token")
                        refreshToken = response.getString("refresh_token")
                    } catch (e: JSONException) {
                        message = response.getString("message")
                    }

                    if (message == null) {
                        view.loginSuccess(accessToken, refreshToken)
                    } else {
                        view.hideLoading()
                        view.loginFailed(message ?: "")
                    }
                }

                override fun onError(anError: ANError) {
                    view.hideLoading()
                    view.loginFailed("Login Failed! Please try again later")
                }

            })
    }

    fun getProfile(accToken: String) {
        AndroidNetworking.get(RefillWaterAPI.getProfile())
            .addHeaders("Accept", "application/json")
            .addHeaders("Authorization", "Bearer $accToken")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    val obj = response.getJSONObject("data")
                    val user = gson.fromJson<User>(
                        gson.fromJson(obj.toString(), JsonElement::class.java),
                        User::class.java
                    )

                    view.loadProfile(user)
                }

                override fun onError(anError: ANError) {

                }
            })
    }
}




