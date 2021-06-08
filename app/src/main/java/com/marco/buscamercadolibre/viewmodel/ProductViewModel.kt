package com.marco.buscamercadolibre.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marco.buscamercadolibre.model.product.ResultModel
import com.marco.buscamercadolibre.repository.ProductProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Clase ViewModal para la búsqueda de productos
 */
@HiltViewModel
class ProductViewModel @Inject constructor(private val productProvider: ProductProvider) : ViewModel() {

    private val TAG = "ProductViewModel"

    val productLiveData = MutableLiveData<ResultModel>()
    val pbLiveData = MutableLiveData<Boolean>()

    /**
     * Inicia una petición de búsqueda de productos, la clase [ProductProvider] determina el destino
     */
    fun searchProducts(query:String){
        viewModelScope.launch {
            pbLiveData.postValue(true)
            val response = productProvider.searchProducts(product = query)

            pbLiveData.postValue(false)
            if(response.isSuccessful){
                productLiveData.postValue(response.body() ?: ResultModel(emptyList()))
            }else{
                Log.e(TAG, response.message())
                productLiveData.postValue(ResultModel(emptyList()))
            }
        }
    }
}