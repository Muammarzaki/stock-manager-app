package com.github.sma.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.sma.ui.dto.StockItem
import com.github.sma.ui.theme.StockmanagerappTheme
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditItemScreen(
    itemToEdit: StockItem? = null,
    onSaveItem: (StockItem) -> Unit,
    onNavigateBack: () -> Unit
) {
    var name by remember { mutableStateOf(itemToEdit?.name ?: "") }
    var quantity by remember { mutableStateOf(itemToEdit?.quantity?.toString() ?: "") }
    var unit by remember { mutableStateOf(itemToEdit?.unit ?: "pcs") }
    var purchasePrice by remember { mutableStateOf(itemToEdit?.purchasePrice?.toString() ?: "") }
    var sellingPrice by remember { mutableStateOf(itemToEdit?.sellingPrice?.toString() ?: "") }
    var supplier by remember { mutableStateOf(itemToEdit?.supplier ?: "") }

    val isEditMode = itemToEdit != null
    val title = if (isEditMode) "Edit Barang" else "Tambah Barang Baru"

    var nameError by remember { mutableStateOf<String?>(null) }
    var quantityError by remember { mutableStateOf<String?>(null) }
    var purchasePriceError by remember { mutableStateOf<String?>(null) }
    var sellingPriceError by remember { mutableStateOf<String?>(null) }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    fun validateFields(): Boolean {
        nameError = if (name.isBlank()) "Nama barang tidak boleh kosong" else null
        quantityError =
            if (quantity.isBlank() || quantity.toIntOrNull() == null || quantity.toInt() < 0) "Jumlah tidak valid" else null
        purchasePriceError =
            if (purchasePrice.isBlank() || purchasePrice.toDoubleOrNull() == null || purchasePrice.toDouble() < 0) "Harga beli tidak valid" else null
        sellingPriceError =
            if (sellingPrice.isBlank() || sellingPrice.toDoubleOrNull() == null || sellingPrice.toDouble() < 0) "Harga jual tidak valid" else null

        return nameError == null && quantityError == null && purchasePriceError == null && sellingPriceError == null
    }


    Scaffold(
        topBar = {
            @OptIn(ExperimentalMaterial3Api::class)
            (androidx.compose.material3.TopAppBar(
                title = { Text(title) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Kembali")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = androidx.compose.material3.MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = androidx.compose.material3.MaterialTheme.colorScheme.onPrimaryContainer
                ),
            ))
        }, snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it; nameError = null },
                    label = { Text("Nama Barang*") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    isError = nameError != null,
                    supportingText = { if (nameError != null) Text(nameError!!) }
                )
            }
            item {
                OutlinedTextField(
                    value = quantity,
                    onValueChange = { quantity = it; quantityError = null },
                    label = { Text("Jumlah*") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    isError = quantityError != null,
                    supportingText = { if (quantityError != null) Text(quantityError!!) }
                )
            }
            item {
                OutlinedTextField(
                    value = unit,
                    onValueChange = { unit = it },
                    label = { Text("Satuan (pcs, kg, liter, dll.)") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
            }
            item {
                OutlinedTextField(
                    value = purchasePrice,
                    onValueChange = { purchasePrice = it; purchasePriceError = null },
                    label = { Text("Harga Beli*") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    leadingIcon = { Text("Rp") },
                    isError = purchasePriceError != null,
                    supportingText = { if (purchasePriceError != null) Text(purchasePriceError!!) }
                )
            }
            item {
                OutlinedTextField(
                    value = sellingPrice,
                    onValueChange = { sellingPrice = it; sellingPriceError = null },
                    label = { Text("Harga Jual*") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    leadingIcon = { Text("Rp") },
                    isError = sellingPriceError != null,
                    supportingText = { if (sellingPriceError != null) Text(sellingPriceError!!) }
                )
            }
            item {
                OutlinedTextField(
                    value = supplier,
                    onValueChange = { supplier = it },
                    label = { Text("Pemasok (Opsional)") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
            }
            item {
                Button(
                    onClick = {
                        if (validateFields()) {
                            val newItem = StockItem(
                                id = itemToEdit?.id ?: 0.toString(),
                                name = name,
                                quantity = quantity.toInt(),
                                unit = unit,
                                purchasePrice = purchasePrice.toDouble(),
                                sellingPrice = sellingPrice.toDouble(),
                                supplier = supplier.takeIf { it.isNotBlank() },
                                lastUpdated = SimpleDateFormat(
                                    "dd-MM-yyyy HH:mm",
                                    Locale.getDefault()
                                ).format(Date())
                            )
                            onSaveItem(newItem)
                        } else {
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    message = "Harap perbaiki semua error sebelum menyimpan.",
                                )
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(if (isEditMode) "Simpan Perubahan" else "Tambah Barang")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddEditItemScreenPreview() {
    StockmanagerappTheme {
        AddEditItemScreen(onSaveItem = {}, onNavigateBack = {})
    }
}

@Preview(showBackground = true)
@Composable
fun AddEditItemScreenEditModePreview() {
    StockmanagerappTheme {
        AddEditItemScreen(
            itemToEdit = StockItem(
                id = "1",
                name = "Buku Tulis",
                quantity = 10,
                unit = "pcs",
                purchasePrice = 2000.0,
                sellingPrice = 2500.0,
                supplier = "PT Stationery",
                lastUpdated = "10-10-2023 10:00"
            ),
            onSaveItem = {}, onNavigateBack = {}
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun AddEditItemScreenWithErrorPreview() {
    StockmanagerappTheme {
        val name = ""
        val quantity = "-5"
        val purchasePrice = ""
        val sellingPrice = "abc"

        val nameError = if (name.isBlank()) "Nama barang tidak boleh kosong" else null
        val quantityError =
            if (quantity.isBlank() || quantity.toIntOrNull() == null || quantity.toInt() < 0) "Jumlah tidak valid" else null
        val purchasePriceError =
            if (purchasePrice.isBlank() || purchasePrice.toDoubleOrNull() == null || purchasePrice.toDouble() < 0) "Harga beli tidak valid" else null
        val sellingPriceError =
            if (sellingPrice.isBlank() || sellingPrice.toDoubleOrNull() == null || sellingPrice.toDouble() < 0) "Harga jual tidak valid" else null

        Scaffold(
            topBar = {
                @OptIn(ExperimentalMaterial3Api::class)
                (androidx.compose.material3.TopAppBar(
                    title = { Text("Tambah Barang Baru (Error)") },
                    navigationIcon = {
                        IconButton(onClick = {}) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Kembali")
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = androidx.compose.material3.MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = androidx.compose.material3.MaterialTheme.colorScheme.onPrimaryContainer
                    )
                )
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { /* no-op for preview */ },
                    label = { Text("Nama Barang*") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    isError = nameError != null,
                    supportingText = { if (nameError != null) Text(nameError) }
                )
                OutlinedTextField(
                    value = quantity,
                    onValueChange = { /* no-op for preview */ },
                    label = { Text("Jumlah*") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    isError = quantityError != null,
                    supportingText = { if (quantityError != null) Text(quantityError) }
                )
                OutlinedTextField(
                    value = purchasePrice,
                    onValueChange = { /* no-op for preview */ },
                    label = { Text("Harga Beli*") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    leadingIcon = { Text("Rp") },
                    isError = purchasePriceError != null,
                    supportingText = { if (purchasePriceError != null) Text(purchasePriceError) }
                )
                OutlinedTextField(
                    value = sellingPrice,
                    onValueChange = { /* no-op for preview */ },
                    label = { Text("Harga Jual*") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    leadingIcon = { Text("Rp") },
                    isError = sellingPriceError != null,
                    supportingText = { if (sellingPriceError != null) Text(sellingPriceError) }
                )
                Spacer(Modifier.weight(1f)) // Pushes button to the bottom
                Button(
                    onClick = { /* no-op */ },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Tambah Barang")
                }
            }
        }
    }
}