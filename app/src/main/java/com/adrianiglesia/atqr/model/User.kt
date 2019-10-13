package com.adrianiglesia.atqr.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    @SerializedName("birth")
    val birth: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("document")
    val document: Int,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("mail")
    val mail: String,
    @SerializedName("password")
    val password: String
) : Parcelable