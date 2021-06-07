package com.marco.buscamercadolibre.repository.network

import com.marco.buscamercadolibre.model.product.ResultModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApiClient {
    @GET("/products/search")
    suspend fun searchProducts(
        @Query("site_id")site: String = "MLM",
        @Query("status")status: String = "active",
        @Query("q")product: String)
    : Response<ResultModel>
}