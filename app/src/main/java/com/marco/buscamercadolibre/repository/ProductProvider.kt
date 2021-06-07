package com.marco.buscamercadolibre.repository

import com.marco.buscamercadolibre.model.product.ResultModel
import com.marco.buscamercadolibre.repository.network.ProductApiService

class ProductProvider {

    private val api = ProductApiService()

    suspend fun searchProducts(product: String): ResultModel = api.searchProducts(product)

}