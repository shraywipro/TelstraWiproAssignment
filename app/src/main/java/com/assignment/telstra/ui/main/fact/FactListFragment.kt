package com.assignment.telstra.ui.main.fact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.assignment.telstra.R
import com.assignment.telstra.core.base.BaseFragment
import com.assignment.telstra.core.store.online.models.FactData
import com.assignment.telstra.databinding.FragmentFactListBinding
import com.assignment.telstra.ui.main.MainActivity
import com.assignment.telstra.utils.Utils
import kotlinx.android.synthetic.main.fragment_fact_list.view.*
import javax.inject.Inject

/**
 * @desc This fragment is responsible for getting facts from the server and to present to the user
 */
open class FactListFragment : BaseFragment<FactListFragmentViewModel>(),
    LifecycleOwner {

    lateinit var binding: FragmentFactListBinding

    @Inject
    lateinit var utils: Utils

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    internal lateinit var viewModel: FactListFragmentViewModel

    val isRefreshing: MutableLiveData<Boolean> = MutableLiveData()
    val noData: MutableLiveData<Int> = MutableLiveData()
    val noDataMsg: MutableLiveData<String> = MutableLiveData()
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    lateinit var factListAdapter: FactListAdapter
    val recyclerVisibility: MutableLiveData<Int> = MutableLiveData()

    override fun getViewModel(): FactListFragmentViewModel {
        viewModel = ViewModelProviders.of(this, factory).get(FactListFragmentViewModel::class.java)
        return viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        factListAdapter = FactListAdapter(context!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_fact_list, container, false)
        binding.viewModel = viewModel
        binding.fragment = this

        binding.lifecycleOwner = viewLifecycleOwner
        val view: View = binding.root

        initUI()
        return view
    }

    /**
     * @desc method to init the UI elements
     */
    private fun initUI() {
        binding.recyclerFactList.layoutManager =
            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)

        getFactsList(false)
    }

    fun getFactsList(bool: Boolean) {
        if (bool)
            showSwipeRefreshing()

        onRetrieveFactListStart()

        if (utils.isNetworkAvailable(context!!)) {

            viewModel.getFactsList().observe(viewLifecycleOwner, Observer { data ->
                if (bool)
                    dismissSwipeRefresh()

                onRetrieveFactListFinish()

                if (data != null) {
                    (activity as MainActivity).updateTitle(data.title!!)
                    if (data.rows?.isNotEmpty()!!) {
                        showNoDataMsg(false, "")
                        refreshAdapter(data.rows!!)
                    } else {
                        showNoDataMsg(true, getString(R.string.no_facts_found))
                    }
                } else {
                    showNoDataMsg(true, getString(R.string.no_facts_found))
                }
            })
        } else {
            showNoDataMsg(true, getString(R.string.no_network))
            if (bool)
                dismissSwipeRefresh()

            onRetrieveFactListFinish()

        }
    }

    //refresh list adapter
    private fun refreshAdapter(list: List<FactData>) {
        factListAdapter.refreshList(addNotNullPosition(list))
    }

    //method to add not null position in list
    private fun addNotNullPosition(list: List<FactData>): List<FactData> {
        val dataList: MutableList<FactData> = mutableListOf()

        for (i in list.indices) {
            if ((list.get(i).title != null) || (list.get(i).decription != null) || (list.get(i).imageUrl != null)) {
                dataList.add(list.get(i))
            }
        }
        return dataList
    }

    //swipe refresh listener
    fun onRefreshListener() {
        getFactsList(true)
    }

    /**
     * @desc method to start refreshing
     */
    private fun showSwipeRefreshing() {
        isRefreshing.value = true
    }

    /**
     * @desc method to dismiss refreshing
     */
    private fun dismissSwipeRefresh() {
        if (isRefreshing.value!!)
            isRefreshing.value = false
    }

    private fun showNoDataMsg(flag: Boolean, msg: String) {
        if (flag) {
            noData.value = 0
            recyclerVisibility.value = 8
            noDataMsg.value = msg
            onRetrieveFactListFinish()
        } else {
            noData.value = 8
            noDataMsg.value = msg
            recyclerVisibility.value = 0
            onRetrieveFactListFinish()
        }
    }

    private fun onRetrieveFactListStart() {
        loadingVisibility.value = 0
    }

    private fun onRetrieveFactListFinish() {
        if (loadingVisibility.value == 0)
            loadingVisibility.value = 8
    }

    fun testFactsList() : Boolean{
        var bool : Boolean
        bool=true

        viewModel.getFactsList().observe(viewLifecycleOwner, Observer { data ->
            bool = data != null
        })
        return bool
    }

     fun testProgressBar():Boolean {
        loadingVisibility.value = 8
        return false
    }


}