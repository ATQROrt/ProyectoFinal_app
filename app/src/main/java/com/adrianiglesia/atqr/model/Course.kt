package com.adrianiglesia.atqr.model


import com.google.gson.annotations.SerializedName

data class Course(
        @SerializedName("asignature")
        val asignature: Asignature,
        @SerializedName("id")
        val id: Int
)
