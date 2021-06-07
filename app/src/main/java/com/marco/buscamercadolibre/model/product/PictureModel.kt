package com.marco.buscamercadolibre.model.product

import com.google.gson.annotations.SerializedName

/**
 * Almacena el id y la url de una imagen para un producto
 */
data class PictureModel(
    @SerializedName("id")
    val id: String,
    @SerializedName("url")
    val url: String,
)
