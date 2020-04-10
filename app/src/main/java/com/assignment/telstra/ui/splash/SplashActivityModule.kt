package com.assignment.telstra.ui.splash

import androidx.lifecycle.ViewModelProvider
import com.assignment.telstra.utils.ViewModelProviderFactory
import dagger.Module
import dagger.Provides

@Module
class SplashActivityModule {

    @Provides
    fun providesViewModel() : SplashViewModel{
        return SplashViewModel()
    }

    @Provides
    fun provideViewModelProvider(viewModel: SplashViewModel) : ViewModelProvider.Factory{
        return ViewModelProviderFactory(viewModel)
    }
}