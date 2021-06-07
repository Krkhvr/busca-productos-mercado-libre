package com.marco.buscamercadolibre.repository.network

import com.marco.buscamercadolibre.model.product.ResultModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Definición de métodos para consumir las API de Mercado Libre
 */
interface ProductApiClient {

    /**
     * Busca productos de acuerdo a una cadena de texto
     * @return Devuelve un response con la lista de productos obtenidos de la consulta realizada
     */
    @GET("/products/search")
    suspend fun searchProducts(
        @Query("site_id")site: String = "MLM",
        @Query("status")status: String = "active",
        @Query("q")product: String)
    : Response<ResultModel>
}