package com.adrianiglesia.atqr.services

import com.adrianiglesia.atqr.model.response.LoginBody
import com.adrianiglesia.atqr.model.response.ResponseService

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiServices {

    @POST("student/testlogin")
    fun userLogin(@Body body: LoginBody): Call<ResponseService>
}
