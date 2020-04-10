package com.assignment.telstra.core.dagger.modules

import android.content.Context
import com.assignment.telstra.TelstraAssignmenetApplication
import com.assignment.telstra.core.store.AppStore
import com.assignment.telstra.core.store.offline.OfflineStore
import com.assignment.telstra.core.store.online.OnlineStore
import com.assignment.telstra.utils.Utils
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideContext(application: TelstraAssignmenetApplication) : Context{
        return application
    }

    @Singleton
    @Provides
    fun provideAppStore(onlineStore: OnlineStore, offlineStore: OfflineStore) : AppStore{
        return AppStore(onlineStore = onlineStore, offlineStore = offlineStore)
    }

    @Singleton
    @Provides
    fun provideGson() : Gson{
        return Gson()
    }

    @Singleton
    @Provides
    fun provideUtils(context: Context) : Utils {
        return Utils(context = context)
    }

}