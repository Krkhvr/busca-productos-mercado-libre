package com.marco.buscamercadolibre.repository.network

import android.util.Log
import com.marco.buscamercadolibre.common.RetrofitHelper
import com.marco.buscamercadolibre.model.product.ResultModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.math.log

class ProductApiService {

    private val TAG = "ProductApiService"

    private val retrofit = RetrofitHelper.getRetrofit()

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