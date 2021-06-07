package com.marco.buscamercadolibre.repository.network

import android.util.Log
import com.marco.buscamercadolibre.common.RetrofitHelper
import com.marco.buscamercadolibre.model.product.ResultModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.math.log

/**
 * Gestiona las llamadas a las APIS de Mercado Libre
 */
class ProductApiService {

    private val TAG = "ProductApiService"

    private val retrofit = RetrofitHelper.getRetrofit()

    /**
     * Busca productos de acuerdo con la cadena recibida
     * @param product Indica el elemento por el cual se desea realizar la búsqueda
     * @return Devuelve un response con la lista de productos obtenidos de la consulta realizada
     */
    suspend fun searchProducts(product: String): ResultModel{
        val response = retrofit.create(ProductApiClient::class.java).searchProducts(product = product)

        return if(response.isSuccessful){
            response.body() ?: ResultModel(emptyList())
        }else{
            Log.e(TAG, response.message())
            ResultModel(emptyList())
        }
    }
}