package com.adrianiglesia.atqr.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class SaveSharedPreference {

    companion object{

        fun getPreferences(context: Context): SharedPreferences {
            return PreferenceManager.getDefaultSharedPreferences(context)
        }

        fun setLoggedIn(context: Context, loggedIn: Boolean, dniUser:String) {
            val editor = getPreferences(context).edit()
            editor.putBoolean("save_login", loggedIn)
            editor.putString("dni_login", dniUser)
            editor.apply()
        }


        fun getLoggedStatus(context: Context): Boolean {
            return getPreferences(context).getBoolean("save_login", false)
        }

        fun getUserDni(context: Context):String {
            return getPreferences(context).getString("dni_login", "")
        }
    }

}