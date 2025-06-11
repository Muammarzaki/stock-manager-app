package com.github.sma.ui.screen


import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.github.sma.ui.dto.StockItem
import com.github.sma.ui.dto.StockTransaction
import com.github.sma.ui.dto.TransactionType


@Preview(showBackground = true, name = "Dashboard Screen Preview")
@Composable
fun DashboardScreenPreview() {
    val dummyStockItems = remember {
        mutableStateListOf(
            StockItem(
                name = "Buku Tulis",
                quantity = 100,
                unit = "pcs",
                purchasePrice = 3000.0,
                sellingPrice = 5000.0,
                supplier = "PT Sinar Dunia",
                lastUpdated = "10-10-2023 10:00",
                id = "1"
            ),
            StockItem(
                name = "Pensil 2B",
                quantity = 250,
                unit = "pcs",
                purchasePrice = 1000.0,
                sellingPrice = 2000.0,
                supplier = "Faber-Castell",
                lastUpdated = "10-10-2023 10:00",
                id = "2"
            ),
            StockItem(
                name = "Penghapus",
                quantity = 150,
                unit = "pcs",
                purchasePrice = 500.0,
                sellingPrice = 1000.0,
                lastUpdated = "10-10-2023 10:00",
                id = "3"
            ),
            StockItem(
                name = "Kopi Sachet",
                quantity = 50,
                unit = "box",
                purchasePrice = 20000.0,
                sellingPrice = 25000.0,
                supplier = "PT Kapal Api",
                lastUpdated = "10-10-2023 10:00",
                id = "4"
            ),
            StockItem(
                name = "Teh Celup",
                quantity = 30,
                unit = "box",
                purchasePrice = 15000.0,
                sellingPrice = 18000.0,
                lastUpdated = "10-10-2023 10:00",
                id = "5"
            )
        )
    }
    MaterialTheme {
        DashboardScreen(
            onNavigateToStockList = {},
            onNavigateToAddItem = {},
            onNavigateToTransactions = {},
            stockItems = dummyStockItems
        )
    }
}

@Preview(showBackground = true, name = "Stock List Screen Preview")
@Composable
fun StockListScreenPreview() {
    val dummyStockItems = remember {
        mutableStateListOf(
            StockItem(
                name = "Buku Tulis",
                quantity = 100,
                unit = "pcs",
                purchasePrice = 3000.0,
                sellingPrice = 5000.0,
                supplier = "PT Sinar Dunia",
                lastUpdated = "10-10-2023 10:00",
                id = "1"
            ),
            StockItem(
                name = "Pensil 2B",
                quantity = 250,
                unit = "pcs",
                purchasePrice = 1000.0,
                sellingPrice = 2000.0,
                supplier = "Faber-Castell",
                lastUpdated = "10-10-2023 10:00",
                id = "2"
            ),
            StockItem(
                name = "Penghapus",
                quantity = 150,
                unit = "pcs",
                purchasePrice = 500.0,
                sellingPrice = 1000.0,
                lastUpdated = "10-10-2023 10:00",
                id = "3"
            ),
            StockItem(
                name = "Kopi Sachet",
                quantity = 50,
                unit = "box",
                purchasePrice = 20000.0,
                sellingPrice = 25000.0,
                supplier = "PT Kapal Api",
                lastUpdated = "10-10-2023 10:00",
                id = "4"
            ),
            StockItem(
                name = "Teh Celup",
                quantity = 30,
                unit = "box",
                purchasePrice = 15000.0,
                sellingPrice = 18000.0,
                lastUpdated = "10-10-2023 10:00",
                id = "5"
            )
        )
    }
    MaterialTheme {
        StockListScreen(items = dummyStockItems, onAddItemClick = {}, onItemClick = {})
    }
}

@Preview(showBackground = true, name = "Add Item Screen Preview")
@Composable
fun AddItemScreenPreview() {
    MaterialTheme {
        AddEditItemScreen(onSaveItem = {}, onNavigateBack = {})
    }
}

@Preview(showBackground = true, name = "Edit Item Screen Preview")
@Composable
fun EditItemScreenPreview() {
    val dummyStockItems = remember {
        mutableStateListOf(
            StockItem(
                name = "Buku Tulis",
                quantity = 100,
                unit = "pcs",
                purchasePrice = 3000.0,
                sellingPrice = 5000.0,
                supplier = "PT Sinar Dunia",
                lastUpdated = "10-10-2023 10:00",
                id = "1"
            ),
            StockItem(
                name = "Pensil 2B",
                quantity = 250,
                unit = "pcs",
                purchasePrice = 1000.0,
                sellingPrice = 2000.0,
                supplier = "Faber-Castell",
                lastUpdated = "10-10-2023 10:00",
                id = "2"
            ),
            StockItem(
                name = "Penghapus",
                quantity = 150,
                unit = "pcs",
                purchasePrice = 500.0,
                sellingPrice = 1000.0,
                lastUpdated = "10-10-2023 10:00",
                id = "3"
            ),
            StockItem(
                name = "Kopi Sachet",
                quantity = 50,
                unit = "box",
                purchasePrice = 20000.0,
                sellingPrice = 25000.0,
                supplier = "PT Kapal Api",
                lastUpdated = "10-10-2023 10:00",
                id = "4"
            ),
            StockItem(
                name = "Teh Celup",
                quantity = 30,
                unit = "box",
                purchasePrice = 15000.0,
                sellingPrice = 18000.0,
                lastUpdated = "10-10-2023 10:00",
                id = "5"
            )
        )
    }
    MaterialTheme {
        AddEditItemScreen(
            itemToEdit = dummyStockItems.first(),
            onSaveItem = {},
            onNavigateBack = {})
    }
}

@Preview(showBackground = true, name = "Item Detail Screen Preview")
@Composable
fun ItemDetailScreenPreview() {
    val dummyStockItems = remember {
        mutableStateListOf(
            StockItem(
                name = "Buku Tulis",
                quantity = 100,
                unit = "pcs",
                purchasePrice = 3000.0,
                sellingPrice = 5000.0,
                supplier = "PT Sinar Dunia",
                lastUpdated = "10-10-2023 10:00",
                id = "1"
            ),
            StockItem(
                name = "Pensil 2B",
                quantity = 250,
                unit = "pcs",
                purchasePrice = 1000.0,
                sellingPrice = 2000.0,
                supplier = "Faber-Castell",
                lastUpdated = "10-10-2023 10:00",
                id = "2"
            ),
            StockItem(
                name = "Penghapus",
                quantity = 150,
                unit = "pcs",
                purchasePrice = 500.0,
                sellingPrice = 1000.0,
                lastUpdated = "10-10-2023 10:00",
                id = "3"
            ),
            StockItem(
                name = "Kopi Sachet",
                quantity = 50,
                unit = "box",
                purchasePrice = 20000.0,
                sellingPrice = 25000.0,
                supplier = "PT Kapal Api",
                lastUpdated = "10-10-2023 10:00",
                id = "4"
            ),
            StockItem(
                name = "Teh Celup",
                quantity = 30,
                unit = "box",
                purchasePrice = 15000.0,
                sellingPrice = 18000.0,
                lastUpdated = "10-10-2023 10:00",
                id = "5"
            )
        )
    }
    MaterialTheme {
        ItemDetailScreen(
            item = dummyStockItems.first(),
            onEditClick = {},
            onDeleteClick = {},
            onNavigateBack = {},
            onAddStock = { _, _ -> },
            onReduceStock = { _, _ -> }
        )
    }
}

@Preview(showBackground = true, name = "Transaction History Screen Preview")
@Composable
fun TransactionHistoryScreenPreview() {
    val dummyTransactions = remember {
        mutableStateListOf(
            StockTransaction(
                itemId = "1",
                itemName = "Buku Tulis",
                type = TransactionType.IN,
                quantityChanged = 50,
                transactionDate = "10-10-2023 10:00",
                id = "1"
            ),
            StockTransaction(
                itemId = "2",
                itemName = "Pensil 2B",
                type = TransactionType.OUT,
                quantityChanged = 20,
                transactionDate = "10-10-2023 10:00",
                id = "2"
            ),
            StockTransaction(
                itemId = "1",
                itemName = "Buku Tulis",
                type = TransactionType.OUT,
                quantityChanged = 10,
                transactionDate = "10-10-2023 10:00",
                id = "3"
            )
        )
    }
    MaterialTheme {
        TransactionHistoryScreen(
            transactions = dummyTransactions,
            onNavigateBack = {}
        )
    }
}

@Preview(showBackground = true, name = "Settings Screen Preview")
@Composable
fun SettingsScreenPreview() {
    MaterialTheme {
        SettingsScreen(onNavigateBack = {})
    }
}

