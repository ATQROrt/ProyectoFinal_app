package com.adrianiglesia.atqr.View

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import com.adrianiglesia.atqr.R
import com.adrianiglesia.atqr.ViewModel.LoginViewModel

import butterknife.ButterKnife
import com.adrianiglesia.atqr.Model.User
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    private var document: String? = null
    private var pass: String = ""

    private var loginViewModel: LoginViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        ButterKnife.bind(this)

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)


        loginViewModel!!.userMutableLiveData.observe(this, Observer<User> {
            if(it != null){
                var intent = Intent(this, MateriasActivity::class.java)
                intent.putExtra("USER", it)
                startActivity(intent)
            }
        })

        loginViewModel!!.apiMessage.observe(this, Observer<String>{
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        })

        bt_login!!.setOnClickListener {
            document =  edt_user.text.toString()
            pass = edt_pass.text.toString()
            if (!document.equals("") || !pass.equals("")) {
                loginViewModel!!.login(document!!.toLong(), pass)
            } else {
                Toast.makeText(this, "Verifique los campos ingresados", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validate(document: Long, pass: String): Boolean {
        return true
    }


}