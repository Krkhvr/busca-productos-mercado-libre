package com.marco.buscamercadolibre.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marco.buscamercadolibre.model.product.ProductModel

/**
 * Clase ViewModal para compartir valores entre fragments.
 */
class SharedViewModel : ViewModel() {

    val selected = MutableLiveData<ProductModel>()

    /**
     * Envía un objeto [ProductModel] hacía un fragment.
     */
    fun sendProduct(product: ProductModel) {
        selected.value = product
    }
}