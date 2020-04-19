package com.assignment.telstra.utils

import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @desc Utils class used for utility operations and methods
 */
@Singleton
open class Utils @Inject constructor(val context: Context) {

    /**
     * @desc This function is used for checking internet connection
     */
    fun isNetworkAvailable(context: Context): Boolean {

        try {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        } catch (e: Exception) {
            return false
        }
    }

}