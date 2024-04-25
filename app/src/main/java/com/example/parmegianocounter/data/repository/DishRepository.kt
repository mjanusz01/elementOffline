package com.example.parmegianocounter.data.repository

import com.example.parmegianocounter.data.model.Dish
import com.example.parmegianocounter.data.model.DishData
import com.example.parmegianocounter.data.model.DishEntity
import kotlinx.coroutines.flow.Flow

interface DishRepository {

    suspend fun downloadDishes(): NetworkResult<DishData>

    fun getDishes(): Flow<List<Dish>>

    suspend fun deleteDishes()

    suspend fun upsertDishes(dishes: List<DishEntity>)

}