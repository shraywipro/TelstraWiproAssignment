package com.assignment.telstra.ui.main

import com.assignment.telstra.core.store.online.services.ApiService
import com.assignment.telstra.utils.Utils
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    fun provideViewModel(apiService: ApiService, utils: Utils) : MainViewModel{
        return MainViewModel(apiService = apiService, utils = utils)
    }
}