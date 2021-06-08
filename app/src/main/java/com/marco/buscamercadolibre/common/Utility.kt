package com.marco.buscamercadolibre.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * Clase de utileria para la aplicación
 */
object Utility {

    /**
     * Válida el estado de la red en el dispositivo
     */
    fun isNetworkConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

}