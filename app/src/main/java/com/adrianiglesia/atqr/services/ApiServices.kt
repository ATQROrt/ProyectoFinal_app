package com.adrianiglesia.atqr.services

import com.adrianiglesia.atqr.model.User
import com.adrianiglesia.atqr.model.response.LoginBody

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiServices {

    @POST("student/login")
    fun userLogin(@Body body: LoginBody): Call<User>
}
