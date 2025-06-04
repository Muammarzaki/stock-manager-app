package com.github.sma.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.sma.ui.component.SummaryCard
import com.github.sma.ui.dto.StockItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    onNavigateToStockList: () -> Unit,
    onNavigateToAddItem: () -> Unit,
    onNavigateToTransactions: () -> Unit,
    stockItems: List<StockItem>
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Dashboard Stock") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SummaryCard(
                title = "Total Jenis Barang",
                value = stockItems.size.toString(),
                icon = Icons.Filled.ShoppingCart
            )
            SummaryCard(
                title = "Barang Hampir Habis",
                value = stockItems.count { it.quantity < 20 }.toString(), // Contoh batas 20
                icon = Icons.Filled.Warning
            )
            SummaryCard(
                title = "Total Nilai Stok (Beli)",
                value = "Rp ${stockItems.sumOf { it.quantity * it.purchasePrice }}",
                icon = Icons.Rounded.Star
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = onNavigateToStockList, modifier = Modifier.fillMaxWidth()) {
                Icon(Icons.Filled.List, contentDescription = "Lihat Daftar Stok")
                Spacer(Modifier.width(8.dp))
                Text("Lihat Daftar Stok")
            }
            Button(onClick = onNavigateToAddItem, modifier = Modifier.fillMaxWidth()) {
                Icon(Icons.Filled.ShoppingCart, contentDescription = "Tambah Barang Baru")
                Spacer(Modifier.width(8.dp))
                Text("Tambah Barang Baru")
            }
            Button(onClick = onNavigateToTransactions, modifier = Modifier.fillMaxWidth()) {
                Icon(Icons.Filled.Build, contentDescription = "Lihat Riwayat Transaksi")
                Spacer(Modifier.width(8.dp))
                Text("Riwayat Transaksi")
            }
        }
    }
}
