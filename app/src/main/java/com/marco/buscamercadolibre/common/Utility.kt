package com.marco.buscamercadolibre.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log

/**
 * Clase de utileria para la aplicación
 */
object Utility {

    const val TAG = "Utility"
    /**
     * Válida el estado de la red en el dispositivo
     */
    fun isNetworkConnected(context: Context): Boolean {
        try{
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
            return activeNetwork?.isConnectedOrConnecting == true
        }catch (e: Exception){
            Log.e(TAG, e.stackTraceToString())
        }
        return false
    }
}