package com.adrianiglesia.atqr.model.response


import com.adrianiglesia.atqr.model.Materia
import com.google.gson.annotations.SerializedName

data class MateriaResponse(
        @SerializedName("asignature")
        val asignature: Materia,
        @SerializedName("id")
        val id: Int

)
