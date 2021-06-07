package com.marco.buscamercadolibre.model.product

import com.google.gson.annotations.SerializedName

data class ResultModel(
    @SerializedName("results")
    val products:List<ProductModel>
)
