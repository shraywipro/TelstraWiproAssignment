package com.assignment.telstra

import com.assignment.telstra.core.dagger.components.DaggerMainComponent
import com.assignment.telstra.core.store.AppStore
import com.assignment.telstra.core.store.online.services.ApiService
import dagger.android.DaggerApplication
import javax.inject.Inject

class TelstraAssignmenetApplication : DaggerApplication() {

    @Inject
    lateinit var appStore: AppStore

    @Inject
    lateinit var apiService: ApiService

    companion object {

        private lateinit var instance: TelstraAssignmenetApplication

        fun getInstance(): TelstraAssignmenetApplication {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

    }

    override fun applicationInjector() = DaggerMainComponent.builder().create(this)
}