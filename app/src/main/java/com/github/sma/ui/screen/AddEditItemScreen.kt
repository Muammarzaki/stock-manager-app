package com.github.sma.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.github.sma.ui.dto.StockItem
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

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
            TopAppBar(
                title = { Text(title) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Kembali")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.Companion
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
                    modifier = Modifier.Companion.fillMaxWidth(),
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
                    modifier = Modifier.Companion.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Companion.Number),
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
                    modifier = Modifier.Companion.fillMaxWidth(),
                    singleLine = true
                )
            }
            item {
                OutlinedTextField(
                    value = purchasePrice,
                    onValueChange = { purchasePrice = it; purchasePriceError = null },
                    label = { Text("Harga Beli*") },
                    modifier = Modifier.Companion.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Companion.Number),
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
                    modifier = Modifier.Companion.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Companion.Number),
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
                    modifier = Modifier.Companion.fillMaxWidth(),
                    singleLine = true
                )
            }
            item {
                Button(
                    onClick = {
                        if (validateFields()) {
                            val newItem = StockItem(
                                id = itemToEdit?.id ?: UUID.randomUUID().toString(),
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
                        }
                    },
                    modifier = Modifier.Companion.fillMaxWidth()
                ) {
                    Text(if (isEditMode) "Simpan Perubahan" else "Tambah Barang")
                }
            }
        }
    }
}