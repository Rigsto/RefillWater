package com.mobiledevelopment.ucrefillsystem.presenter

import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.google.gson.Gson
import com.mobiledevelopment.ucrefillsystem.helper.CoroutineContextProvider
import com.mobiledevelopment.ucrefillsystem.network.ApiRepository
import com.mobiledevelopment.ucrefillsystem.viewinterface.LoginView
import com.mobiledevelopment.ucrefillsystem.viewinterface.ProfileView
import org.json.JSONObject

class ProfilePresenter( view: ProfileView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun edit (email: String?){
        AndroidNetworking.post("https://refill.fusionsvisual.com/api/edit-profile")
            .addHeaders("Accept", "application/json")
            .addBodyParameter("email",email)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {

                    val message = response?.getString("message")

                    if (message == "The given data was invalid."){

                    }



                }

                override fun onError(anError: ANError?) {

                    Log.d("ONERROR",anError?.errorDetail?.toString())

                }


            })
    }

}