package com.adrianiglesia.atqr.model.response

import com.adrianiglesia.atqr.model.Course
import com.google.gson.annotations.SerializedName

class CourseResponse (
        @SerializedName("course")
        val course: Course,
        @SerializedName("percentage")
        val percentage:Int
)