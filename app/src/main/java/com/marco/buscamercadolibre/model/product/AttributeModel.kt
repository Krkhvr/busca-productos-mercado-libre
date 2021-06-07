package com.marco.buscamercadolibre.model.product

import com.google.gson.annotations.SerializedName

data class AttributeModel(
    @SerializedName("name")
    val name: String,
    @SerializedName("value_name")
    val value: String)
