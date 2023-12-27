package com.example.githubrepoapp.data.cache.database

import androidx.room.TypeConverter
import com.example.githubrepoapp.data.cache.models.OwnerCacheEntity
import com.google.gson.Gson

class DataConverter {

        @TypeConverter
        fun modelToJson(value: OwnerCacheEntity?) = Gson().toJson(value)

        @TypeConverter
        fun jsonToModel(value: String) = Gson().fromJson(value, OwnerCacheEntity::class.java)
}