package com.github.sma.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.sma.ui.component.StockItemRow
import com.github.sma.ui.dto.StockItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockListScreen(
    items: List<StockItem>,
    onAddItemClick: () -> Unit,
    onItemClick: (StockItem) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    val filteredItems = items.filter {
        it.name.contains(searchQuery, ignoreCase = true) ||
                (it.supplier?.contains(searchQuery, ignoreCase = true) ?: false)
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dashboard Stok Barang") },
                navigationIcon = {
                    Icon(
                        Icons.Filled.Inventory2,
                        contentDescription = "Dashboard Icon",
                        modifier = Modifier.padding(start = 12.dp)
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddItemClick) {
                Icon(Icons.Filled.Add, contentDescription = "Tambah Barang")
            }
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Cari barang...") },
                leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Ikon Cari") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.LightGray
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
            if (filteredItems.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Tidak ada barang dalam stok atau hasil pencarian kosong.")
                }
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(filteredItems, key = { it.id }) { item ->
                        StockItemRow(item = item, onClick = { onItemClick(item) })
                    }
                }
            }
        }
    }
}