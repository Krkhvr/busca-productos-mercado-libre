package com.marco.buscamercadolibre.view.main

import androidx.navigation.NavController
import com.marco.buscamercadolibre.model.product.ProductModel

/**
 * Interface to get the click event in RecyclerView
 */
interface OnProductClickListener {
    fun onProductClicked(product: ProductModel)
}