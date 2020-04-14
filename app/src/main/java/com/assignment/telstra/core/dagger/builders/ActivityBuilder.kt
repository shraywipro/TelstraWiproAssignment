package com.assignment.telstra.core.dagger.builders

import com.assignment.telstra.ui.main.MainActivity
import com.assignment.telstra.ui.main.MainActivityModule
import com.assignment.telstra.ui.main.fact.FactListFragmentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainActivityModule::class, FactListFragmentProvider::class])
    abstract fun contributeMainActivity(): MainActivity


}