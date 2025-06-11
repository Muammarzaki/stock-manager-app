package com.github.sma.ui.vm

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.sma.domain.Product
import com.github.sma.domain.ProductUseCase
import com.github.sma.ui.dto.StockItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val productUseCase: ProductUseCase) : ViewModel() {

    var stockItems = mutableStateListOf<StockItem>()
        private set

    init {
        loadStockItems()
    }

    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    fun loadStockItems() {
        viewModelScope.launch(Dispatchers.IO) {
            val products = productUseCase.getAll()

            val items = products.map { product ->
                StockItem(
                    name = product.name,
                    quantity = product.quantity,
                    unit = product.unit,
                    purchasePrice = product.purchasePrice,
                    sellingPrice = product.sellingPrice,
                    supplier = product.supplier ?: "",
                    lastUpdated = product.lastUpdated,
                    id = product.id.toString()
                )
            }

            stockItems.clear()
            stockItems.addAll(items)
        }
    }

    private val _searchResults = MutableStateFlow<List<Product>>(emptyList())
    val searchResults: StateFlow<List<Product>> = _searchResults

    fun searchProducts(query: String) {
        _searchQuery.value = query
        viewModelScope.launch(Dispatchers.IO) {
            if (query.isNotEmpty()) {
                _searchResults.value = productUseCase.searchProducts(query)
            } else {
                _searchResults.value = productUseCase.getAll()
            }
        }
    }

    fun addStockItem(item: StockItem) {
        viewModelScope.launch(Dispatchers.IO) {
            productUseCase.add(item.toProduct())
            loadStockItems()
        }
    }

    fun getStockItemById(id: Int): StockItem? {
        return stockItems.find { it.id == id.toString() }
    }

    fun updateStockItem(item: StockItem) {
        viewModelScope.launch(Dispatchers.IO) {
            productUseCase.update(item.toProduct())
            loadStockItems()
        }
    }

    fun deleteStockItem(item: StockItem) {
        viewModelScope.launch(Dispatchers.IO) {
            productUseCase.delete(item.id.toInt())
            loadStockItems()
        }
    }
}