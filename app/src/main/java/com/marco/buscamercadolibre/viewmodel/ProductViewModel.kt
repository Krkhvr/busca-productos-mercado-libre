package com.marco.buscamercadolibre.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marco.buscamercadolibre.model.product.ProductModel
import com.marco.buscamercadolibre.model.product.ResultModel
import com.marco.buscamercadolibre.repository.ProductProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Clase ViewModal para la búsqueda de productos
 */
class ProductViewModel: ViewModel() {

    val productLiveData = MutableLiveData<ResultModel>()
    val pbLiveData = MutableLiveData<Boolean>()

    private val productProvider = ProductProvider()

    /**
     * Inicia una petición de búsqueda de productos, la clase [ProductProvider] determina el destino
     */
    fun searchProducts(query:String){
        viewModelScope.launch {
            pbLiveData.postValue(true)
            val products = productProvider.searchProducts(product = query)
            productLiveData.postValue(products)
            pbLiveData.postValue(false)
        }
    }
}