package com.github.sma.ui.dto

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

data class StockItem(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    var quantity: Int,
    val unit: String, // contoh: pcs, kg, liter
    var purchasePrice: Double,
    var sellingPrice: Double,
    val supplier: String? = null,
    val lastUpdated: String = SimpleDateFormat(
        "dd-MM-yyyy HH:mm",
        Locale.getDefault()
    ).format(Date())
)