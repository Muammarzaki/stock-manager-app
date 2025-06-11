package com.github.sma.ui.dto

import com.github.sma.domain.Product
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class StockItem(
    val id: String,
    val name: String,
    var quantity: Int,
    val unit: String,
    var purchasePrice: Double,
    var sellingPrice: Double,
    val supplier: String? = null,
    val lastUpdated: String = SimpleDateFormat(
        "dd-MM-yyyy HH:mm",
        Locale.getDefault()
    ).format(Date())
) {
    fun toProduct(): Product {
        return Product(
            id = id.toInt(),
            name = name,
            quantity = quantity,
            unit = unit,
            purchasePrice = purchasePrice,
            sellingPrice = sellingPrice,
            supplier = supplier,
            lastUpdated = lastUpdated
        )
    }
}