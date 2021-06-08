package com.marco.buscamercadolibre.view.main

import com.marco.buscamercadolibre.model.product.ProductModel

/**
 * Intercae para obtener el evento click dentro de la lista de productos
 */
interface OnProductClickListener {

    /**
     * Evento Click en un elemento de la lista
     * @param product Objeto [ProductModel] del cual se mostrarán sus detalles en un nuevo fragment
     */
    fun onProductClicked(product: ProductModel)
}