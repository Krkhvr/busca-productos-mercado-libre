package com.marco.buscamercadolibre.model.product

import com.google.gson.annotations.SerializedName

data class ProductModel(
    @SerializedName("id")
    val id: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("domain_id")
    val domainId: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("attributes")
    val attributeModel: List<AttributeModel>,
    @SerializedName("pictures")
    val pictures:List<PictureModel>
)
