package com.assignment.telstra.ui.main.fact

import androidx.lifecycle.ViewModelProvider
import com.assignment.telstra.core.store.online.services.ApiService
import com.assignment.telstra.utils.ViewModelProviderFactory
import dagger.Module
import dagger.Provides

@Module
class FactListFragmentModule {

    @Provides
    fun provideViewModel(repository: FactListRepository): FactListFragmentViewModel {
        return FactListFragmentViewModel(repository)
    }

    @Provides
    fun provideRepository(apiService: ApiService): FactListRepository {
        return FactListRepository(apiService)
    }


    @Provides
    fun provideViewModelProvider(viewModel: FactListFragmentViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(viewModel)
    }

}