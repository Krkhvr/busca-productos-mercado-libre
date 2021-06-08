package com.marco.buscamercadolibre.di.module

import com.marco.buscamercadolibre.model.product.ProductModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

/**
 * Módulo de dependencias para el [MainFragment]
 */
@Module
@InstallIn(FragmentComponent::class)
object MainFragmentModule {

    /**
     * Crea una instancia de una lista de productos
     * @return Devuelve un [ArrayList] para un objeto [ProductModel]
     */
    @Provides
    fun provideProductList() = ArrayList<ProductModel>()
}