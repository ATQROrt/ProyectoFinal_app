package com.adrianiglesia.atqr.services

import com.adrianiglesia.atqr.model.Assistance
import com.adrianiglesia.atqr.model.User
import com.adrianiglesia.atqr.model.response.LoginBody
import com.adrianiglesia.atqr.model.Course
import com.adrianiglesia.atqr.model.response.CourseResponse
import com.adrianiglesia.atqr.model.response.QrBody
import okhttp3.ResponseBody

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiServices {

    @POST("student/login")
    fun userLogin(@Body body: LoginBody): Call<User>

    @POST("course/student")
    fun userAssignatures(@Body body: User): Call<List<CourseResponse>>

    @POST("course/assistance")
    fun sendQr(@Body body: QrBody): Call<ResponseBody>


    @POST("course/history")
    fun  getAssistance(@Body body:QrBody): Call<List<Assistance>>
}
