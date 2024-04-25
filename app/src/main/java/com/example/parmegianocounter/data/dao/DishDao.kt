package com.example.parmegianocounter.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.parmegianocounter.data.model.DishEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DishDao {

    @Query(value = "SELECT * FROM dishes")
    fun getDishEntities(): Flow<List<DishEntity>>

    @Upsert
    fun upsertDishes(dish: List<DishEntity>)

}