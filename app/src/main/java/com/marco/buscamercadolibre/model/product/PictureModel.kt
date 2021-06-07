package com.marco.buscamercadolibre.model.product

import com.google.gson.annotations.SerializedName

data class PictureModel(
    @SerializedName("id")
    val id: String,
    @SerializedName("url")
    val url: String,
)
