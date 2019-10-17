package com.adrianiglesia.atqr.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.adrianiglesia.atqr.model.Assistance
import com.adrianiglesia.atqr.model.response.QrBody
import com.adrianiglesia.atqr.services.MainRepository

class AsistenciaViewModel:ViewModel() {

    private val repository = MainRepository()

    private val assistance:MutableLiveData<List<Assistance>> = MutableLiveData()
    private var message:MutableLiveData<String> = MutableLiveData()
    var isLoading:MutableLiveData<Boolean> = MutableLiveData()

    fun getMyAsistance(body: QrBody):LiveData<List<Assistance>>{

        loadAssistance(body)
        return assistance
    }

    private fun loadAssistance(body: QrBody){

        repository.getAssistance(body,{

            isLoading.value = true
            assistance.value = it

        },{

            isLoading.value = false
            message.value = it

        })

    }

    fun showMessage():LiveData<String>{
        return message
    }


}