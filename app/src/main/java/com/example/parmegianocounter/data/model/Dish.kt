package com.example.parmegianocounter.data.model

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class Dish(
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "additional")
    val additional: String,
    @field:Json(name = "price")
    val price: Int,
)