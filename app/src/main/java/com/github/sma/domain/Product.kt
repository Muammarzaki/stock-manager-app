package com.github.sma.domain

data class Product(
    val id: Int,
    val name: String,
    val quantity: Int,
    val unit: String,
    val sellingPrice: Double,
    val purchasePrice: Double,
    val supplier: String? = null,
    val lastUpdated: String
)
