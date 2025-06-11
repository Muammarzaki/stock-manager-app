package com.github.sma.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
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
            TopAppBar(
                title = {
                    Text(
                        "Stock Manager",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                text = "Ringkasan Stok",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary
            )

            // Summary Cards in a Row or Grid
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                SummaryCardModern(
                    title = "Total Jenis Barang",
                    value = stockItems.size.toString(),
                    icon = Icons.Filled.ShoppingCart,
                    modifier = Modifier.weight(1f)
                )
                SummaryCardModern(
                    title = "Hampir Habis",
                    value = stockItems.count { it.quantity < 20 }.toString(), // Example threshold
                    icon = Icons.Filled.Warning,
                    modifier = Modifier.weight(1f),
                    highlightColor = MaterialTheme.colorScheme.errorContainer
                )
            }
            SummaryCardModern(
                title = "Total Nilai Stok (Beli)",
                value = "Rp ${
                    stockItems.sumOf { it.quantity * it.purchasePrice }
                        .toString()
                        .replace(Regex("(\\d)(?=(\\d{3})+$)"), "$1.")
                }",
                icon = Icons.Rounded.Star,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Aksi Cepat",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary
            )

            OutlinedButton(onClick = onNavigateToStockList, modifier = Modifier.fillMaxWidth()) {
                Icon(Icons.AutoMirrored.Filled.List, contentDescription = "Lihat Daftar Stok")
                Spacer(Modifier.width(8.dp))
                Text("Lihat Daftar Stok")
            }
            OutlinedButton(onClick = onNavigateToAddItem, modifier = Modifier.fillMaxWidth()) {
                Icon(Icons.Filled.ShoppingCart, contentDescription = "Tambah Barang Baru")
                Spacer(Modifier.width(8.dp))
                Text("Tambah Barang Baru")
            }
            OutlinedButton(onClick = onNavigateToTransactions, modifier = Modifier.fillMaxWidth()) {
                Icon(Icons.Filled.Build, contentDescription = "Lihat Riwayat Transaksi")
                Spacer(Modifier.width(8.dp))
                Text("Riwayat Transaksi")
            }
        }
    }
}

@Composable
fun SummaryCardModern(
    title: String,
    value: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    modifier: Modifier = Modifier,
    highlightColor: Color = MaterialTheme.colorScheme.secondaryContainer
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = highlightColor)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Icon(
                icon,
                contentDescription = title,
                tint = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Text(
                text = value,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
    }
}
