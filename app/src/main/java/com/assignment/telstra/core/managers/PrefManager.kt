package com.assignment.telstra.core.managers

import android.content.Context
import android.content.SharedPreferences
import com.assignment.telstra.utils.Constants
import com.google.gson.Gson
import javax.inject.Inject

/**
 * @desc this core class is responsible to save and reterive data in preferences
 */
class PrefManager  @Inject constructor(val context: Context, val gson: Gson){
    private var sharedPrefrences: SharedPreferences
    private var editor: SharedPreferences.Editor

    init {
        sharedPrefrences = context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE)
        editor = sharedPrefrences.edit()
        editor.apply()
    }

    fun setFirstTimeLaunch(isFirstTime: Boolean) {
        editor.putBoolean(Constants.IS_FIRST_TIME_LAUNCH, isFirstTime)
        editor.commit()
    }

    fun isFirstTimeLaunch(): Boolean {
        return sharedPrefrences.getBoolean(Constants.IS_FIRST_TIME_LAUNCH, true)
    }

    fun clearPreference() {
        editor.clear()
        editor.apply()
    }


}