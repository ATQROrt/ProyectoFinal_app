package com.adrianiglesia.atqr.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View.GONE
import android.view.View.VISIBLE

import com.adrianiglesia.atqr.R
import com.adrianiglesia.atqr.viewmodel.LoginViewModel

import butterknife.ButterKnife
import com.adrianiglesia.atqr.model.User
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    private var document: Long? = null
    private var pass: String = ""

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        ButterKnife.bind(this)

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)


        loginViewModel.getMessage().observe(this, Observer<String>{
            showMessage(it!!)
        })

        loginViewModel.isLoading.observe(this,Observer<Boolean>{
            setVisiblities(it!!)
        })

        loginViewModel.userMutableLiveData.observe(this, Observer<User> {
            if(it != null){
                val intent = Intent(this, MateriasActivity::class.java)
                intent.putExtra("USER",it)
                startActivity(intent)
                finish()
            }
        })


        bt_login.setOnClickListener {
            document =  edt_user.text.toString().toLong()
            pass = edt_pass.text.toString()
            if (document != null || pass != "") {
                loginViewModel.login(document!!, pass)
            }
        }
    }

    private fun setVisiblities(it:Boolean){
        if(it){
            edt_user.isEnabled = false
            edt_pass.isEnabled = false
            loading_login.visibility = VISIBLE
            bt_login.visibility = GONE
        }else{
            edt_user.isEnabled = true
            edt_pass.isEnabled = true
            loading_login.visibility = GONE
            bt_login.visibility = VISIBLE
        }
    }
    private fun showMessage(message:String){
        Snackbar.make(login_layout, message, Snackbar.LENGTH_SHORT).show()
    }

}
