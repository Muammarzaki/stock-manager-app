package com.github.sma.domain

interface ProductUseCase {
    suspend fun getAll(): List<Product>
    suspend fun get(id: Int): Product?
    suspend fun add(product: Product)
    suspend fun update(product: Product)
    suspend fun delete(id: Int)
    suspend fun searchProducts(string: String): List<Product>
}