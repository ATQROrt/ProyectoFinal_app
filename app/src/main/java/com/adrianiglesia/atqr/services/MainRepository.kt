package com.adrianiglesia.atqr.services


import android.util.Log
import com.adrianiglesia.atqr.model.Assistance
import com.adrianiglesia.atqr.model.User
import com.adrianiglesia.atqr.model.response.LoginBody
import com.adrianiglesia.atqr.model.response.CourseResponse
import com.adrianiglesia.atqr.model.response.QrBody
import okhttp3.ResponseBody

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful && response.body() != null) {
                    val userResponse = response.body()!!
                    successHandler(userResponse)
                } else {
                    failureHandler("Error al recuperar el usuario. Verefique datos ingresados")
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                failureHandler(t.message.toString())
            }
        })

    }

    fun userAssignature(user:User, successHandler: (List<CourseResponse>)->Unit, failureHandler: (String)-> Unit){
        val assignatures:Call<List<CourseResponse>> = services.userAssignatures(user)
        assignatures.enqueue(object : Callback<List<CourseResponse>>{
            override fun onFailure(call: Call<List<CourseResponse>>, t: Throwable) {
                failureHandler(t.message.toString())
            }

            override fun onResponse(call: Call<List<CourseResponse>>, response: Response<List<CourseResponse>>) {
                if (response.isSuccessful && response.body() != null) {
                    val assignatureResponse = response.body()!!
                    successHandler(assignatureResponse)
                }else{
                    failureHandler("Error al recuperar las materias. Intente mas tarde")

                }
            }

        })
    }

    fun sendQr(qrBody:QrBody,successHandler: (String)->Unit, failureHandler: (String)-> Unit){
        val sendQr:Call<ResponseBody> = services.sendQr(qrBody)
        sendQr.enqueue(object : Callback<ResponseBody>{
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                failureHandler(t.message.toString())
            }
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if(response.isSuccessful && response.body() != null){
                    successHandler("PRESENTE")
                }


            }

        })
    }


    fun getAssistance(body:QrBody,successHandler: (List<Assistance>) -> Unit, failureHandler: (String) -> Unit){
        val assistance: Call<List<Assistance>> = services.getAssistance(body)
        assistance.enqueue(object : Callback<List<Assistance>>{
            override fun onFailure(call: Call<List<Assistance>>, t: Throwable) {
                failureHandler(t.message.toString())
            }
            override fun onResponse(call: Call<List<Assistance>>, response: Response<List<Assistance>>) {
                if(response.isSuccessful && response.body() != null){
                    Log.d("RESPONSE", response.body().toString())
                    successHandler(response.body()!!)
                }
            }

        })

    }
}
