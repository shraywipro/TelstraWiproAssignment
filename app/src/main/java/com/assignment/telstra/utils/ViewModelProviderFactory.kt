package com.assignment.telstra.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelProviderFactory<V: ViewModel>(private val viewModel: V) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(viewModel::class.java))
            return  viewModel as T
        throw IllegalAccessException("Unknown class name")
    }

}