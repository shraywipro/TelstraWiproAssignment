package com.assignment.telstra.ui.main.fact

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.assignment.telstra.core.store.online.models.FactData
import com.assignment.telstra.core.store.online.models.FactModel
import javax.inject.Inject

class FactListFragmentViewModel @Inject constructor(val repository: FactListRepository) : ViewModel(){

    private lateinit var responseLiveData: LiveData<FactModel?>

    fun getFactsList() : LiveData<FactModel?> {
        responseLiveData = repository.getFactsList()
        return  responseLiveData
    }

}