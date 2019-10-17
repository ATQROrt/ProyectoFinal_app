package com.adrianiglesia.atqr.services

import com.adrianiglesia.atqr.model.User
import com.adrianiglesia.atqr.model.response.LoginBody
import com.adrianiglesia.atqr.model.response.MateriaResponse
import com.adrianiglesia.atqr.model.response.QrBody
import okhttp3.ResponseBody

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {

    @POST("student/login/")
    fun userLogin(@Body body: LoginBody): Call<User>

    @POST("course/student")
    fun userAssignatures(@Body body: User): Call<List<MateriaResponse>>

    @POST("course/assistance")
    fun sendQr(@Body body: QrBody): Call<ResponseBody>
}
