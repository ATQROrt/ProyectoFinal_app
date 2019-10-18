package com.adrianiglesia.atqr.model

import com.google.gson.annotations.SerializedName
import java.util.*

class Assistance(
        @SerializedName("date")
        val date:Long,
        @SerializedName("assistanceStatus")
        val status:AssistanceStatus,
        @SerializedName("cancelled")
        val cancelled:Boolean)


enum class AssistanceStatus {

    @SerializedName("PRESENT")
    PRESENT,
    @SerializedName("ABSENT")
    ABSENT
}