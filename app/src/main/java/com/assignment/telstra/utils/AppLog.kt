package com.assignment.telstra.utils

import android.util.Log
import com.assignment.telstra.BuildConfig

object AppLog {

    private val APP_DEBUG = BuildConfig.DEBUG

    fun debugE(message: String) {
        if (APP_DEBUG) {
            Log.e(Constants.LOG_TAG, message)
        }
    }

    fun debugE(className: String, message: String) {
        if (APP_DEBUG) {
            Log.e(Constants.LOG_TAG, "$className  :  $message")
        }
    }


    fun debugV(message: String) {
        if (APP_DEBUG) {
            Log.v(Constants.LOG_TAG, message)
        }
    }

    fun debugV(className: String, message: String) {
        if (APP_DEBUG) {
            Log.v(Constants.LOG_TAG, className + " : " + message)
        }
    }


    fun debugD(message: String) {
        if (APP_DEBUG) {
            Log.d(Constants.LOG_TAG, message)
        }
    }

    fun debugD(className: String, message: String) {
        if (APP_DEBUG) {
            Log.d(Constants.LOG_TAG, className + " : " + message)
        }
    }


    fun println(message: String) {
        if (APP_DEBUG) {
            println(Constants.LOG_TAG + message)
        }
    }

    fun loadStackTrace(e: Exception) {
        if (APP_DEBUG) {
            e.printStackTrace()
        }
    }


}
