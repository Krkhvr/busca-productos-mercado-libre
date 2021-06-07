package com.marco.buscamercadolibre.repository

import com.marco.buscamercadolibre.model.product.ResultModel
import com.marco.buscamercadolibre.repository.network.ProductApiService

/**
 * Gestiona hacía que repositorios se realizará la petición de información o datos
 */
class ProductProvider {

    //Define el recurso de consulta
    private val api = ProductApiService()

    /**
     * Realiza una petición de acuerdo al recurso implementado
     * @return Devuelve un response con la lista de productos obtenidos de la consulta realizada
     */
    suspend fun searchProducts(product: String): ResultModel = api.searchProducts(product)

}