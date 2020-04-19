package com.assignment.telstra

import androidx.lifecycle.*
import com.assignment.telstra.core.store.online.models.FactData
import com.assignment.telstra.core.store.online.models.FactModel
import com.assignment.telstra.core.store.online.services.ApiService
import com.assignment.telstra.databinding.FragmentFactListBinding
import com.assignment.telstra.ui.main.fact.FactListFragment
import com.assignment.telstra.ui.main.fact.FactListFragmentViewModel
import com.assignment.telstra.ui.main.fact.FactListRepository
import io.reactivex.disposables.CompositeDisposable
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import javax.inject.Inject


class FactListFragmentTest {

    private var count: Int = 0
    lateinit var fragment: FactListFragment

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    lateinit var provider: ViewModelProvider
    lateinit var viewModel: FactListFragmentViewModel

    lateinit var mViewModelStore: ViewModelStore
    lateinit var repository: FactListRepository

    lateinit var lifecycleowner: LifecycleOwner

    lateinit var apiService: ApiService

    lateinit var factModel: FactModel

    lateinit var factData: FactData

    lateinit var responseLiveData: LiveData<FactModel>


    var responseMutableLiveData = MutableLiveData<FactModel?>()
    lateinit var disposable : CompositeDisposable


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        fragment = Mockito.mock(FactListFragment::class.java)
        mViewModelStore = Mockito.mock(ViewModelStore::class.java)
        provider = Mockito.mock(ViewModelProvider::class.java)
        factory = Mockito.mock(ViewModelProvider.Factory::class.java)
        lifecycleowner = Mockito.mock(LifecycleOwner::class.java)
        viewModel = Mockito.mock(FactListFragmentViewModel::class.java)
        repository = Mockito.mock(FactListRepository::class.java)

        factData = Mockito.mock(FactData::class.java)
        factModel = Mockito.mock(FactModel::class.java)
        disposable=Mockito.mock(CompositeDisposable::class.java)

        apiService = Mockito.mock(ApiService::class.java)
      //  responseLiveData=Mockito.mock(MutableLiveData<FactModel>::class.java)
//        viewModel=ViewModelProviders.of(fragment, factory).get(FactListFragmentViewModel::class.java)

        viewModel = FactListFragmentViewModel(repository)
        viewModel.getFactsList().observe(lifecycleowner, Observer { data ->
            count = data!!.rows!!.size
        })
    }

    @Test
    fun testJson() {

        //`when`(fragment.testFactsList()).thenReturn(null)
        assertNotNull(count)
        //assertTrue(fragment.testFactsList().hasObservers())

    }


}