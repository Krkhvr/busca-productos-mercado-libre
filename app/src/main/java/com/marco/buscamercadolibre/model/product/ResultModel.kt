package com.marco.buscamercadolibre.model.product

import com.google.gson.annotations.SerializedName

/**
 * Almacena el response al momento de realizar una búsqueda de productos a la API de Mercado Libre
 */
data class ResultModel(
    @SerializedName("results")
    val products:List<ProductModel>
)
