package com.example.parmegianocounter.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "dishes")
data class DishEntity(
    @PrimaryKey val name: String,
    @ColumnInfo(name = "additional") val additional: String,
    @ColumnInfo(name = "price") val price: Int,
)