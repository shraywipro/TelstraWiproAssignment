package com.assignment.telstra.core.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.lifecycle.ViewModel
import com.assignment.telstra.core.store.AppStore
import com.assignment.telstra.utils.Utils
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 * @desc Its Base class for all the activities in the application and it will have all the common method
 */
abstract class BaseActivity<T : ViewModel> : DaggerAppCompatActivity() {

    internal var provider: String = ""

    @Inject
    lateinit var utils: Utils
    @Inject
    lateinit var appStore: AppStore

    private lateinit var viewModel: T

    abstract fun getViewModel(): T

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        this.viewModel = if (viewModel == null) getViewModel() else viewModel
    }

}