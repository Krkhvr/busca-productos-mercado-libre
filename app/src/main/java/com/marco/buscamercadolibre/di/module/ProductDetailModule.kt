package com.marco.buscamercadolibre.di.module

import com.marco.buscamercadolibre.model.product.PictureModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

/**
 * Módulo de dependencias para [ProductDetailFragment]
 */
@Module
@InstallIn(FragmentComponent::class)
object ProductDetailModule {

    /**
     * Provee una instancia de una lista de imágenes
     * @return Devuelve un [ArrayList] para el objeto [PictureModel]
     */
    @Provides
    fun providePictureList() = ArrayList<PictureModel>()
}