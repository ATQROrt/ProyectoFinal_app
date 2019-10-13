package com.adrianiglesia.atqr.Model

import com.google.gson.annotations.SerializedName

data class Materia(
        @SerializedName("name")
        val name:String,
        val percent:String
)