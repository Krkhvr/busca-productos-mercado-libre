package com.marco.buscamercadolibre.common

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Clase auxiliar para métodos utilizados junto con Retrofit
 */
object RetrofitHelper {

    /**
     * Crea un nuevo objeto para consultas REST
     * @return Devuelve un objeto [Retrofit]]
     */
    fun getRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}