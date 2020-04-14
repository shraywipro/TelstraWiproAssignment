package com.assignment.telstra.ui.main.fact

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FactListFragmentProvider {

    @ContributesAndroidInjector(modules = [FactListFragmentModule::class])
    abstract fun provideFactListFragmentFactory(): FactListFragment
}