package com.marco.buscamercadolibre.view.main

import com.marco.buscamercadolibre.model.product.ProductModel
import javax.inject.Inject

/***
 * Clase que implementa el evento OnClick para un elemento de la lista de productos
 */
class OnProductClickListenerImp @Inject constructor(private val onProductClickListener: OnProductClickListener){

    /***
     * Listener para el evento onClick de un producto
     * @return Devuelve el evento OnClick
     */
    fun onProductClicked(product: ProductModel) = onProductClickListener.onProductClicked(product)
}