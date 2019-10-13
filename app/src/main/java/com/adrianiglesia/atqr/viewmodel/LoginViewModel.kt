package com.adrianiglesia.atqr.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

import com.adrianiglesia.atqr.model.User
import com.adrianiglesia.atqr.services.MainRepository

class LoginViewModel : ViewModel() {

    private val repository = MainRepository()
    private var userDoc:Long = 0
    private var userPass:String = ""

    var userMutableLiveData:MutableLiveData<User> = MutableLiveData()
    var apiMessage:MutableLiveData<String> = MutableLiveData()


    fun login(document: Long, pass: String){
        userDoc = document
        userPass = pass

        callLogin(userDoc, userPass)
    }


    private fun callLogin(document: Long, pass: String) {
        repository.userLogin(document, pass, {
            userMutableLiveData.value = it
        }, {
            apiMessage.value = it
        } )
    }

}
