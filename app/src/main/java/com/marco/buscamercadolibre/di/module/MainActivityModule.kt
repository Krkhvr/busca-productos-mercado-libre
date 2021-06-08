package com.marco.buscamercadolibre.di.module

import com.marco.buscamercadolibre.repository.ProductProvider
import com.marco.buscamercadolibre.repository.network.ProductApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
class MainActivityModule {

    @Provides
    fun provideProductProvider(productApiService: ProductApiService) = ProductProvider(productApiService)

}