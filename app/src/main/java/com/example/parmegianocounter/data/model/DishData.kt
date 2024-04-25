package com.example.parmegianocounter.data.model

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class DishData(
    @field:Json(name = "dishes")
    val dishes: List<Dish>
)