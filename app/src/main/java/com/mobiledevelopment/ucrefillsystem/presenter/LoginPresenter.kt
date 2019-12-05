package com.mobiledevelopment.ucrefillsystem.presenter

import com.google.gson.Gson
import com.mobiledevelopment.ucrefillsystem.model.response.UsersResponse
import com.mobiledevelopment.ucrefillsystem.network.ApiRepository
import com.mobiledevelopment.ucrefillsystem.network.RefillWaterAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginPresenter (private val view : MainView,
                               private val apiRepository: ApiRepository,
                               private val gson: Gson
        ){
            fun getUserList (name : String?){
                view.showLoading()

                GlobalScope.launch(Dispatchers.Main){
                    val data = gson.fromJson(apiRepository.doRequest(RefillWaterAPI.getUser(name)).await(), UsersResponse::class.java)

                    view.showNameList(data.users)
                    view.hideLoading()
                }

    }

    fun SendUser(name :String?) {
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