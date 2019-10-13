package com.adrianiglesia.atqr.Model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName

import java.util.Date
import kotlinx.android.parcel.Parcelize


@Parcelize
data class User(
        @SerializedName("firstName")
        var firstName: String? = null,
        @SerializedName("lastName")
        var lastName: String? = null,
        @SerializedName("document")
        var document: Long? = null,
        @SerializedName("mail")
        var mail: String? = null,
        @SerializedName("birth")
        var birth: Date? = null,
        @SerializedName("imageUrl")
        var imageUrl: String? = null,
        @SerializedName("password")
        var password: String? = null) : Parcelable