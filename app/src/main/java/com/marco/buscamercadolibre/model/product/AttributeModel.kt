package com.marco.buscamercadolibre.model.product

import com.google.gson.annotations.SerializedName

/**
 * Atributos de un producto
 */
data class AttributeModel(
    @SerializedName("name")
    val name: String,
    @SerializedName("value_name")
    val value: String)
