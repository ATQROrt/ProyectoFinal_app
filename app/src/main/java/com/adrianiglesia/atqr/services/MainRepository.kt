package com.adrianiglesia.atqr.services


import com.adrianiglesia.atqr.model.User
import com.adrianiglesia.atqr.model.response.LoginBody

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
        login.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: retrofit2.Response<User>) {
                if (response.isSuccessful && response.body() != null) {
                    val userResponse = response.body()!!
                    successHandler(userResponse)
                } else {
                    failureHandler("Error al recuperar el usuario. Verefique datos ingresados")
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                failureHandler("ERROR AL LLAMAR AL SERVICIO")
            }
        })

    }


}
