package com.assignment.telstra.core.dagger.components

import com.assignment.telstra.TelstraAssignmenetApplication
import com.assignment.telstra.core.dagger.builders.ActivityBuilder
import com.assignment.telstra.core.dagger.modules.AppModule
import com.assignment.telstra.core.dagger.modules.NetworkModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, NetworkModule::class, ActivityBuilder::class])
interface MainComponent : AndroidInjector<TelstraAssignmenetApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<TelstraAssignmenetApplication>()
}