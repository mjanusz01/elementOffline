package com.example.parmegianocounter.data.model

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