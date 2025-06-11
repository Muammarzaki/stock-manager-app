package com.github.sma.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    var quantity: Int,
    val unit: String,
    @ColumnInfo(name = "purchase_price")
    var purchasePrice: Double,
    @ColumnInfo(name = "selling_price")
    var sellingPrice: Double,
    val supplier: String? = null,
    @ColumnInfo(name = "last_updated")
    val lastUpdated: String
)
