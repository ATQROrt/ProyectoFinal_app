package com.adrianiglesia.atqr.model

import com.google.gson.annotations.SerializedName

data class Materia(
        @SerializedName("name")
        val name:String,
        val percent:String
)