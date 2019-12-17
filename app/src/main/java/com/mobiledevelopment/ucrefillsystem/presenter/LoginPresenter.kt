package com.mobiledevelopment.ucrefillsystem.presenter

import android.util.Log
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.coroutines.awaitStringResponse
import com.github.kittinunf.fuel.coroutines.awaitStringResponseResult
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import com.google.gson.Gson
import com.mobiledevelopment.ucrefillsystem.helper.CoroutineContextProvider
import com.mobiledevelopment.ucrefillsystem.model.TokenAccess
import com.mobiledevelopment.ucrefillsystem.model.User
import com.mobiledevelopment.ucrefillsystem.model.response.LoginResponse
import com.mobiledevelopment.ucrefillsystem.network.ApiRepository
import com.mobiledevelopment.ucrefillsystem.network.RefillWaterAPI
import com.mobiledevelopment.ucrefillsystem.viewinterface.LoginView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

import okhttp3.*

import okhttp3.OkHttpClient
import java.io.IOException
import com.google.gson.JsonObject
import kotlinx.coroutines.runBlocking
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONObject
import java.lang.Exception


class LoginPresenter(
    private val view: LoginView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()

) {
    fun login(email: String, password: String) {
    view.showLoading()
/*
        val bodyJson = """
  { "email" : '$email',
    "password" : '$password'
  }
"""
        println(bodyJson)
        runBlocking {
            val (request, response, result) = Fuel.post("https://refill.fusionsvisual.com/api/login")
                .body(bodyJson)
                .awaitStringResponse()
            println(response)

        } */

        AndroidNetworking.post("https://refill.fusionsvisual.com/api/login")
            .addHeaders("Accept", "application/json")
            .addBodyParameter("email",email)
            .addBodyParameter("password",password)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {

                    val message = response?.getString("message")

                    if (message == "Account disabled" || message == "Please verify email address"){
                        view.hideLoading()
                        view.loginFailed(message)
                    }

                    view.hideLoading()
                    view.loginSuccess("Kim Min Ju", "8000")

                }

                override fun onError(anError: ANError?) {
                    view.hideLoading()
                    Log.d("ONERROR",anError?.errorDetail?.toString())
                    view.loginFailed("Login Failed! Please try again later")
            }


            })





        }

    fun getdata(email : String?){
        AndroidNetworking.get("https://refill.fusionsvisual.com/api/profile")
            .addPathParameter("email",email)
            .setPriority(Priority.LOW)
                .build()
                    .getAsJSONObject(object : JSONObjectRequestListener{
                        override fun onResponse(response: JSONObject?) {

                            println(response)




                        }

                        override fun onError(anError: ANError?) {
                            view.hideLoading()
                            println("error here")
                            Log.d("ONERROR",anError?.errorDetail?.toString())

                        }
                    })
        /*
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository.doRequest(
                    RefillWaterAPI.GetProfile(
                        email
                    )


                ).await(), User::class.java
            )



            if (data != null) {
                view.loginFailed()
                view.hideLoading()

            } else {
                view.hideLoading()
                view.loginSuccess(data)
            }

    }
    */
    }


        /*
        if (response.equals("Account disabled")) {
            view.loginFailed()
            view.hideLoading()
        } else {

            /*
            GlobalScope.launch(context.main) {
                val data = gson.fromJson(
                    apiRepository.doRequest(
                        RefillWaterAPI.Login(
                            email,
                            password
                        )


                    ).await(), LoginResponse::class.java
                )



                if (data.message.equals("Account disabled")) {
                    view.loginFailed()
                    view.hideLoading()
                } else {

                    view.loginSuccess(mediaType, "")
                }
            }

            */ */

        }




