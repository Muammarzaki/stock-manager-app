package com.github.sma.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ProductEntity::class], version = 1, exportSchema = true)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao

    companion object {
        @Volatile
        private var INSTANCE: ProductDatabase? = null

        fun getInstance(applicationContext: Context): ProductDatabase {
            INSTANCE = Room.databaseBuilder(
                applicationContext,
                ProductDatabase::class.java,
                "product_database"
            ).build()
            return INSTANCE as ProductDatabase
        }
    }
}