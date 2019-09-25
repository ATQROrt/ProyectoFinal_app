package com.adrianiglesia.atqr.ViewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

import com.adrianiglesia.atqr.Model.User
import com.adrianiglesia.atqr.Services.MainRepository

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
