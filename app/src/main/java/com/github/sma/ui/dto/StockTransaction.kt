package com.github.sma.ui.dto

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

data class StockTransaction(
    val id: String = UUID.randomUUID().toString(),
    val itemId: String,
    val itemName: String,
    val type: TransactionType,
    val quantityChanged: Int,
    val transactionDate: String = SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault()).format(
        Date()
    )
)