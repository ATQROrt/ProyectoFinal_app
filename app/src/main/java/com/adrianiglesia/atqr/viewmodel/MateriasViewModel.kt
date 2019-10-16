package com.adrianiglesia.atqr.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.adrianiglesia.atqr.model.User
import com.adrianiglesia.atqr.model.response.MateriaResponse
import com.adrianiglesia.atqr.services.MainRepository

class MateriasViewModel: ViewModel() {

    private val repository = MainRepository()

    private var assignatures:MutableLiveData<List<MateriaResponse>> = MutableLiveData()
    var isLoading:MutableLiveData<Boolean> = MutableLiveData()
    private var message:MutableLiveData<String> = MutableLiveData()


    fun getAssignatures(user: User):LiveData<List<MateriaResponse>>{

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

}