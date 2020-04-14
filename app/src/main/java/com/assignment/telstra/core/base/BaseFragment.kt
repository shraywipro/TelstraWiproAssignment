package com.assignment.telstra.core.base

import android.content.Context
import androidx.lifecycle.ViewModel
import com.assignment.telstra.core.store.AppStore
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * @desc Its Base class for all the fragments in the application and it will have all the common methods
 */
abstract class BaseFragment<T : ViewModel> : DaggerFragment() {

    @Inject
    lateinit var appStore: AppStore

    private lateinit var viewModel: T

    abstract fun getViewModel(): T

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.viewModel = getViewModel()
    }

}