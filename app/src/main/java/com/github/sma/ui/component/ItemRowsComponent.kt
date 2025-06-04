package com.github.sma.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.sma.ui.dto.StockItem
import com.github.sma.ui.dto.StockTransaction
import com.github.sma.ui.dto.TransactionType

@Composable
fun TransactionItemRow(transaction: StockTransaction) {
    Card(modifier = Modifier.Companion.fillMaxWidth()) {
        Row(
            modifier = Modifier.Companion
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.Companion.CenterVertically
        ) {
            Icon(
                imageVector = if (transaction.type == TransactionType.IN) Icons.AutoMirrored.Filled.ArrowForward else Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = transaction.type.name,
                tint = if (transaction.type == TransactionType.IN) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
            )
            Spacer(modifier = Modifier.Companion.width(16.dp))
            Column(modifier = Modifier.Companion.weight(1f)) {
                Text(transaction.itemName, style = MaterialTheme.typography.titleMedium)
                Text(
                    "${transaction.type}: ${transaction.quantityChanged} pcs",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Text(transaction.transactionDate, style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
fun StockItemRow(item: StockItem, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(item.name, style = MaterialTheme.typography.titleMedium)
                Text(
                    "Sisa: ${item.quantity} ${item.unit}",
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    "Harga Jual: Rp ${item.sellingPrice}",
                    style = MaterialTheme.typography.bodySmall
                )
                item.supplier?.let {
                    Text(
                        "Pemasok: $it",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
            }
            Icon(Icons.Filled.Home, contentDescription = "Lihat Detail")
        }
    }
}


@Composable
fun DetailRow(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "$label: ",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(text = value, style = MaterialTheme.typography.bodyLarge)
    }
}
