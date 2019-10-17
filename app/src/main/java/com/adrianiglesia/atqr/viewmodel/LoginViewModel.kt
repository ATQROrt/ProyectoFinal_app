package com.adrianiglesia.atqr.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.adrianiglesia.atqr.model.User

import com.adrianiglesia.atqr.services.MainRepository

class LoginViewModel : ViewModel() {

    private val repository = MainRepository()

    var isLoading:MutableLiveData<Boolean> = MutableLiveData()
    var userMutableLiveData:MutableLiveData<User> = MutableLiveData()
    var message:MutableLiveData<String> = MutableLiveData()


    fun login(document: Long, pass: String){
        when {
            validate(document,pass) -> callLogin(document, pass)
            else -> message.value = "Verifique los campos ingresados"
        }
    }


    private fun callLogin(document: Long, pass: String) {
        isLoading.value = true

        repository.userLogin(document, pass, {
            message.value = "Exito!"
            userMutableLiveData.value = it
            isLoading.value = false


        }, {

            message.value = it
            isLoading.value = false

        } )
    }

    private fun validate(doc:Long, pass:String):Boolean{
        var valid = true

        if (doc == null || doc.toString().length < 8 ) {
            valid = false
        }

        if (pass.isEmpty() || pass.length < 8 || pass.length > 16) {
            valid = false
        }

        return valid
    }

}
