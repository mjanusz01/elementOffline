package com.example.parmegianocounter.data.repository

import com.example.parmegianocounter.data.dao.DishDao
import com.example.parmegianocounter.data.model.Dish
import com.example.parmegianocounter.data.model.DishData
import com.example.parmegianocounter.data.model.DishEntity
import com.example.parmegianocounter.data.model.asExternalModel
import com.example.parmegianocounter.data.network.DishDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

data class DishRepositoryImpl(
    private val dishDataSource: DishDataSource,
    private val ioDispatcher: CoroutineDispatcher,
    private val dishDao: DishDao
) : DishRepository{
    override suspend fun downloadDishes(): NetworkResult<DishData> = withContext(ioDispatcher){
        handleNetworkResult {
            dishDataSource.getJokes()
        }
    }

    override fun getDishes(): Flow<List<Dish>> = dishDao.getDishEntities().map{
        it.map(DishEntity::asExternalModel)
    }

    override suspend fun upsertDishes(dishes: List<DishEntity>) = withContext(ioDispatcher){
        dishDao.upsertDishes(dishes)
    }
}