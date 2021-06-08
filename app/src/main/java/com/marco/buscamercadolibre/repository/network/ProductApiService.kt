package com.marco.buscamercadolibre.repository.network

import javax.inject.Inject

/**
 * Gestiona las llamadas a las APIS de Mercado Libre
 */
class ProductApiService @Inject constructor(private val productApiClient: ProductApiClient){

    private val TAG = "ProductApiService"

    /**
     * Busca productos de acuerdo con la cadena recibida
     * @param product Indica el elemento por el cual se desea realizar la búsqueda
     * @return Devuelve un response con la lista de productos obtenidos de la consulta realizada
     */
    suspend fun searchProducts(product: String) = productApiClient.searchProducts(product = product)
}