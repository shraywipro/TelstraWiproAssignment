package com.assignment.telstra.core.store.offline

import com.assignment.telstra.core.managers.PrefManager
import javax.inject.Inject

/**
 * @param prefManager object of Prefrence manager to save and get data from the preference
 * @desc method to manage the offline store of data in preferences
 */
class OfflineStore @Inject constructor(val prefManager: PrefManager) {

    fun setFirstTimeLaunch() {
        prefManager.setFirstTimeLaunch(false)
    }

    fun isFirstTimeLaunch(): Boolean {
        return prefManager.isFirstTimeLaunch()
    }

    fun clearPreference(){
        prefManager.clearPreference()
    }

}