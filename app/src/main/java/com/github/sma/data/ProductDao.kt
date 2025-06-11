package com.github.sma.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE, entity = ProductEntity::class)
    fun insert(product: ProductEntity)

    @Delete(entity = ProductEntity::class)
    fun delete(product: ProductEntity)

    @Query("delete from products where id = :id")
    fun deleteById(id: Int)

    @Update(entity = ProductEntity::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(product: ProductEntity)

    @Query("select * from products")
    suspend fun getAll(): List<ProductEntity>

    @Query("select * from products where id = :id")
    suspend fun findById(id: Int): ProductEntity?

    @Query("select * from products where name like '%' || :string || '%'")
    suspend fun searchProducts(string: String): List<ProductEntity>

}