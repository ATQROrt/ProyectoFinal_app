package com.adrianiglesia.atqr.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.adrianiglesia.atqr.model.Assistance
import com.adrianiglesia.atqr.services.MainRepository

class AsistenciaViewModel:ViewModel() {

    private val repository = MainRepository()

    private val assistance:MutableLiveData<List<Assistance>> = MutableLiveData()
    private var message:MutableLiveData<String> = MutableLiveData()
    var isLoading:MutableLiveData<Boolean> = MutableLiveData()






    fun getMyAsistance():LiveData<List<Assistance>>{

        //revisar que recibe por parametros
        loadAssistance()
        return assistance
    }



    fun loadAssistance(){

        repository.getAssistance({
            //seteo valor de assiatnce y otros
        },{
            //muestro mensaje de error y otros
        })

    }


}