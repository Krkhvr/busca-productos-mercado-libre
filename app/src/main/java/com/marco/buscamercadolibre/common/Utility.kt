package com.marco.buscamercadolibre.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.util.Log
import com.marco.buscamercadolibre.model.product.AttributeModel

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

    /**
     * Construye una lista de atributos de un producto para poder mostrarlos en pantalla en un solo [TextView]
     * @param attributeModel Lista de atributos del producto
     * @return Devueve una cadena concatenada con todos los atributos del producto
     */
    fun getFeatures(attributeModel: List<AttributeModel>): String{
        var features = ""
        attributeModel.forEach {
            features+= "<font color=#3483FA><b>> </b></font>${it.name}: ${it.value}<br>"
        }
        return features
    }

    /**
     * Parsea una cadena para darle formato html
     * @return Devuelve un texto enriquecido
     */
    fun convertTextToHtml(features: String): Spanned {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(features, Html.FROM_HTML_MODE_COMPACT)
        } else {
            Html.fromHtml(features)
        }
    }
}