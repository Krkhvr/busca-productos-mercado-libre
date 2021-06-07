package com.marco.buscamercadolibre.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marco.buscamercadolibre.model.product.ProductModel

class SharedViewModel : ViewModel() {

    val selected = MutableLiveData<ProductModel>()

    fun sendProduct(product: ProductModel) {
        //selected.value = product
        selected.postValue(product)
    }
}