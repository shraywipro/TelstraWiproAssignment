package com.assignment.telstra.core.store

import com.assignment.telstra.core.store.online.OnlineStore
import javax.inject.Inject

/**
 * @desc class to manage the online data received from API
 */
class AppStore @Inject constructor(val onlineStore: OnlineStore)