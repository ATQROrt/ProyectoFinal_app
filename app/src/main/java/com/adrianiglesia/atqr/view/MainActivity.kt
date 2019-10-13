package com.adrianiglesia.atqr.view

import android.content.Intent
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.adrianiglesia.atqr.R

import butterknife.ButterKnife


class MainActivity : AppCompatActivity() {

    private val TIME = 500
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)


        Handler().postDelayed({
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, TIME.toLong())

    }



}
