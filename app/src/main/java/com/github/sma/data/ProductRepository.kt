package com.github.sma.data

import com.github.sma.domain.Product
import com.github.sma.domain.ProductUseCase

class ProductRepository(private val dao: ProductDao) : ProductUseCase {

    private val toProduct: (ProductEntity) -> Product = {
        Product(
            id = it.id,
            name = it.name,
            quantity = it.quantity,
            unit = it.unit,
            purchasePrice = it.purchasePrice,
            sellingPrice = it.sellingPrice,
            supplier = it.supplier,
            lastUpdated = it.lastUpdated
        )
    }

    private val toProductEntity: (Product) -> ProductEntity = {
        ProductEntity(
            id = it.id,
            name = it.name,
            quantity = it.quantity,
            unit = it.unit,
            purchasePrice = it.purchasePrice,
            sellingPrice = it.sellingPrice,
            supplier = it.supplier,
            lastUpdated = it.lastUpdated
        )
    }

    override suspend fun getAll(): List<Product> =
        dao.getAll().map(toProduct)


    override suspend fun get(id: Int): Product? =
        dao.findById(id)?.let {
            toProduct(it)
        }

    override suspend fun add(product: Product) {
        dao.insert(toProductEntity(product))
    }


    override suspend fun update(product: Product) {
        dao.update(toProductEntity(product))
    }

    override suspend fun delete(id: Int) {
        dao.deleteById(id)
    }

    override suspend fun searchProducts(string: String): List<Product> {
        return dao.searchProducts(string).map(toProduct)
    }
}