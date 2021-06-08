package com.marco.buscamercadolibre.repository

import com.marco.buscamercadolibre.repository.network.ProductApiService
import javax.inject.Inject

/**
 * Gestiona hacía que repositorios se realizará la petición de información o datos
 */
class ProductRepository @Inject constructor(private val productApiService: ProductApiService) {

    /**
     * Realiza una petición de acuerdo al recurso implementado
     * @return Devuelve un response con la lista de productos obtenidos de la consulta realizada
     */
    suspend fun searchProducts(product: String) = productApiService.searchProducts(product)
}