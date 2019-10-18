package com.adrianiglesia.atqr.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Asignature(
        @SerializedName("name")
        val name:String,
        @SerializedName("code")
        val code:String
) : Parcelable