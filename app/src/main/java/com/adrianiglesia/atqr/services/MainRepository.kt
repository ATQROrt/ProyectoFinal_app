package com.adrianiglesia.atqr.services


import com.adrianiglesia.atqr.model.response.LoginBody
import com.adrianiglesia.atqr.model.response.ResponseService
import com.adrianiglesia.atqr.model.User

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit

class MainRepository {

    private val services: ApiServices
    private val retrofit: Retrofit = MyRetrofit.getRetrofit()

    init {
        services = retrofit.create(ApiServices::class.java)
    }

    fun userLogin(username: Long?, pass: String, successHandler: (User)->Unit, failureHandler: (String)-> Unit) {

        val body = LoginBody(username, pass)
        val login = services.userLogin(body)
        login.enqueue(object : Callback<ResponseService> {
            override fun onResponse(call: Call<ResponseService>, response: retrofit2.Response<ResponseService>) {
                if (response.isSuccessful && response.body()!!.getObject() != null) {
                    val userResponse = response.body()!!.getObject() as User
                    successHandler(userResponse)
                } else {
                    failureHandler(response.message())
                }
            }

            override fun onFailure(call: Call<ResponseService>, t: Throwable) {
                failureHandler("ERROR AL LLAMAR AL SERVICIO")
            }
        })

    }


}
