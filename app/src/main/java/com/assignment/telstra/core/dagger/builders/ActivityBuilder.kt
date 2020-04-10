package com.assignment.telstra.core.dagger.builders

import com.assignment.telstra.ui.main.MainActivity
import com.assignment.telstra.ui.main.MainActivityModule
import com.assignment.telstra.ui.main.fact.FactListFragmentProvider
import com.assignment.telstra.ui.splash.SplashActivity
import com.assignment.telstra.ui.splash.SplashActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [SplashActivityModule::class])
    abstract fun contributeSplashActivity() : SplashActivity

    @ContributesAndroidInjector(modules = [MainActivityModule::class, FactListFragmentProvider::class])
    abstract fun contributeMainActivity() : MainActivity


}