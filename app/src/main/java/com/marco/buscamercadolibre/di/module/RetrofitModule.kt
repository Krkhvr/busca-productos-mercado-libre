package com.marco.buscamercadolibre.di.module

import com.marco.buscamercadolibre.common.Constants
import com.marco.buscamercadolibre.repository.network.ProductApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    /**
     * Obtiene la url base para las APIS de Mercado Libre
     * @return Devuelve una url en formato [String]
     */
    @Provides
    @Singleton
    fun provideBaseUrl() = Constants.API_URL

    /**
     * Obtiene un conversor json para castear los objetos de una petición
     * @return Devuelve un conversor de objetos de tipo Json a Data
     */
    @Provides
    @Singleton
    fun provideGsonConverterFactory(): Converter.Factory = GsonConverterFactory.create()

    /**
     * Crea un nuevo objeto para consultas REST
     * @return Devuelve un objeto [Retrofit]]
     */

    @Provides
    @Singleton
    fun provideRetrofitInstance(url: String, converter: Converter.Factory): Retrofit =
        Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(converter)
            .build()

    /**
     * Obtiene una implementación para realizar la petición a través de [Retrofit]
     * @return Devuelve una implementación de la interace [ProductApiClient]]
     */
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ProductApiClient{
        return retrofit.create(ProductApiClient::class.java)
    }
}