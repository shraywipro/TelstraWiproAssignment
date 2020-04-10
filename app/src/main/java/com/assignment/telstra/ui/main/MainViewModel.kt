package com.assignment.telstra.ui.main

import androidx.lifecycle.ViewModel
import com.assignment.telstra.core.store.online.services.ApiService
import com.assignment.telstra.utils.Utils
import javax.inject.Inject

class MainViewModel @Inject constructor(val apiService: ApiService, val utils: Utils) : ViewModel() {

    override fun onCleared() {
        super.onCleared()
    }
}