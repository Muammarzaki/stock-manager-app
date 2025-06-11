package com.github.sma.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.sma.domain.ProductUseCase

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val productUseCase: ProductUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(productUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}