package com.adrianiglesia.atqr.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.adrianiglesia.atqr.model.User
import com.adrianiglesia.atqr.model.Course
import com.adrianiglesia.atqr.model.response.CourseResponse
import com.adrianiglesia.atqr.model.response.QrBody
import com.adrianiglesia.atqr.services.MainRepository

class MateriasViewModel: ViewModel() {

    private val repository = MainRepository()

    private var assignatures:MutableLiveData<List<CourseResponse>> = MutableLiveData()
    var isLoading:MutableLiveData<Boolean> = MutableLiveData()
    private var message:MutableLiveData<String> = MutableLiveData()


    fun getAssignatures(user: User):LiveData<List<CourseResponse>>{

        loadAssginatures(user)
        return assignatures
    }

    private fun loadAssginatures(user:User){
        isLoading.value = true
        repository.userAssignature(user,{
            assignatures.value = it
            isLoading.value = false

        },{

            isLoading.value = false
        })

    }

    fun sendQr(qrBody:QrBody){
        repository.sendQr(qrBody,{
            message.value = it
        },{
            message.value  = it
        })
    }

    fun getMessage():LiveData<String>{
        return message
    }

}