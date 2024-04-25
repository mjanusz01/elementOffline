package com.example.parmegianocounter.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.parmegianocounter.data.dao.DishDao
import com.example.parmegianocounter.data.model.DishEntity

@Database(entities = [DishEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dishDao(): DishDao
}