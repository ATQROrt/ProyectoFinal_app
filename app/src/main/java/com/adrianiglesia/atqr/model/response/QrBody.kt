package com.adrianiglesia.atqr.model.response

import com.google.gson.annotations.SerializedName

class QrBody(
        @SerializedName("studentId")
        var studentId:Long,
        @SerializedName("courseId")
        var courseId:Long)