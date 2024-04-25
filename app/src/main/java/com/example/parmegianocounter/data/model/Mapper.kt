package com.example.parmegianocounter.data.model

import kotlin.random.Random

fun DishEntity.asExternalModel() = Dish(
    name = this.name,
    additional = this.additional,
    price = this.price,
)

fun Dish.asEntity() = DishEntity(
    name = this.name,
    additional = this.additional,
    price = this.price
)