package com.adrianiglesia.atqr.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Course(
        @SerializedName("asignature")
        val asignature: Asignature,
        @SerializedName("id")
        val id: Int
) : Parcelable
