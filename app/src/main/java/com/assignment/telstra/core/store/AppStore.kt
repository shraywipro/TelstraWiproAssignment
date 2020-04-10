package com.assignment.telstra.core.store

import com.assignment.telstra.core.store.offline.OfflineStore
import com.assignment.telstra.core.store.online.OnlineStore
import javax.inject.Inject

/**
 * @desc class to manage the store both offline and online data received from API
 */
class AppStore @Inject constructor(val onlineStore: OnlineStore, val offlineStore: OfflineStore){

    fun setFirstTimeLaunch() {
        offlineStore.setFirstTimeLaunch()
    }

    fun isFirstTimeLaunch(): Boolean {
        return offlineStore.isFirstTimeLaunch()
    }

    fun clearPreference(){
        offlineStore.clearPreference()
    }

}