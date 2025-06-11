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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.github.sma.ui.component.DetailRow
import com.github.sma.ui.dto.DialogType
import com.github.sma.ui.dto.StockItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemDetailScreen(
    item: StockItem?,
    onEditClick: (StockItem) -> Unit,
    onDeleteClick: (StockItem) -> Unit,
    onNavigateBack: () -> Unit,
    onAddStock: (StockItem, Int) -> Unit,
    onReduceStock: (StockItem, Int) -> Unit
) {
    if (item == null) return onNavigateBack()
    var showDialog by remember { mutableStateOf(false) }
    var dialogType by remember { mutableStateOf<DialogType?>(null) }
    var quantityChange by remember { mutableStateOf("") }
    var quantityChangeError by remember { mutableStateOf<String?>(null) }



    if (showDialog && dialogType != null) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false; quantityChange = ""; quantityChangeError = null
            },
            title = {
                Text(
                    when (dialogType) {
                        DialogType.ADD -> "Tambah Stok"
                        DialogType.REDUCE -> "Kurangi Stok"
                        DialogType.DELETE -> "Hapus Barang"
                        else -> ""
                    }
                )
            },
            text = {
                when (dialogType) {
                    DialogType.ADD, DialogType.REDUCE -> {
                        Column {
                            Text("Masukkan jumlah untuk ${if (dialogType == DialogType.ADD) "ditambah" else "dikurangi"}:")
                            Spacer(Modifier.Companion.height(8.dp))
                            OutlinedTextField(
                                value = quantityChange,
                                onValueChange = { quantityChange = it; quantityChangeError = null },
                                label = { Text("Jumlah") },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Companion.Number),
                                isError = quantityChangeError != null,
                                supportingText = {
                                    if (quantityChangeError != null) Text(
                                        quantityChangeError!!
                                    )
                                }
                            )
                        }
                    }

                    DialogType.DELETE -> Text("Apakah Anda yakin ingin menghapus barang '${item.name}'? Tindakan ini tidak dapat diurungkan.")
                    else -> {}
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        when (dialogType) {
                            DialogType.ADD -> {
                                val amount = quantityChange.toIntOrNull()
                                if (amount != null && amount > 0) {
                                    onAddStock(item, amount)
                                    showDialog = false
                                    quantityChange = ""
                                } else {
                                    quantityChangeError = "Jumlah tidak valid"
                                }
                            }

                            DialogType.REDUCE -> {
                                val amount = quantityChange.toIntOrNull()
                                if (amount != null && amount > 0 && amount <= item.quantity) {
                                    onReduceStock(item, amount)
                                    showDialog = false
                                    quantityChange = ""
                                } else if (amount != null && amount > item.quantity) {
                                    quantityChangeError = "Jumlah melebihi stok"
                                } else {
                                    quantityChangeError = "Jumlah tidak valid"
                                }
                            }

                            DialogType.DELETE -> {
                                onDeleteClick(item)
                                showDialog = false
                            }

                            else -> {}
                        }
                    }
                ) { Text("Konfirmasi") }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDialog = false; quantityChange = ""; quantityChangeError = null
                }) {
                    Text("Batal")
                }
            }
        )
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(item.name) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Kembali")
                    }
                },
                actions = {
                    IconButton(onClick = { onEditClick(item) }) {
                        Icon(Icons.Filled.Edit, contentDescription = "Edit Barang")
                    }
                    IconButton(onClick = { dialogType = DialogType.DELETE; showDialog = true }) {
                        Icon(Icons.Filled.Delete, contentDescription = "Hapus Barang")
                    }
                }
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("Detail Barang", style = MaterialTheme.typography.titleLarge, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
                        Spacer(modifier = Modifier.height(8.dp))
                        DetailRow(label = "ID Barang", value = item.id)
                        DetailRow(label = "Nama Barang", value = item.name)
                        DetailRow(label = "Jumlah Saat Ini", value = "${item.quantity} ${item.unit}")
                        DetailRow(label = "Harga Beli", value = "Rp ${item.purchasePrice}")
                        DetailRow(label = "Harga Jual", value = "Rp ${item.sellingPrice}")
                        item.supplier?.let { DetailRow(label = "Pemasok", value = it) }
                        DetailRow(label = "Terakhir Diperbarui", value = item.lastUpdated)
                    }
                }

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Manajemen Stok", style = MaterialTheme.typography.titleLarge, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Button(
                                onClick = { dialogType = DialogType.ADD; showDialog = true },
                                modifier = Modifier.weight(1f)
                            ) {
                                Icon(Icons.Filled.AddCircle, contentDescription = "Tambah Stok")
                                Spacer(Modifier.width(8.dp))
                                Text("Tambah")
                            }
                            OutlinedButton(
                                onClick = { dialogType = DialogType.REDUCE; showDialog = true },
                                modifier = Modifier.weight(1f),
                                enabled = item.quantity > 0
                            ) {
                                Icon(Icons.Filled.RemoveCircle, contentDescription = "Kurangi Stok")
                                Spacer(Modifier.width(8.dp))
                                Text("Kurangi")
                            }
                        }
                    }
                }
            }
        }
    }
}