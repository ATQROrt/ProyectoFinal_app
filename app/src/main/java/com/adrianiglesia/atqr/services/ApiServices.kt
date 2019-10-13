package com.adrianiglesia.atqr.services

import com.adrianiglesia.atqr.model.User
import com.adrianiglesia.atqr.model.response.LoginBody
import com.adrianiglesia.atqr.model.response.MateriaResponse

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiServices {

    @POST("student/login")
    fun userLogin(@Body body: LoginBody): Call<User>

    @POST("course/student")
    fun userAssignatures(@Body body: User): Call<List<MateriaResponse>>
}
