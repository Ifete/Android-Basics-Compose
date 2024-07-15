package com.example.courses.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Course(
    @StringRes val courseNameResourceId: Int,
    val classes : Int,
    @DrawableRes val imageResourceId: Int,

)
