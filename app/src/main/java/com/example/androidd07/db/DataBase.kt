package com.example.androidd07.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androidd07.model.Dto.ProductDto

@Database(entities = [ProductDto::class], version = 1)
abstract class DataBase : RoomDatabase() {
    abstract fun getProductDao(): ProductDAO

    companion object {
        @Volatile
        private var INSTANCE: DataBase? = null
        fun getInstance(context: Context): DataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataBase::class.java,
                    "database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}