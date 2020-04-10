package com.assignment.telstra.ui.main.fact

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
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
import javax.inject.Inject

/**
 * @desc This fragment is responsible for getting weather information from the server and to present to the user
 */
open class FactListFragment() : BaseFragment<FactListFragmentViewModel>(), SwipeRefreshLayout.OnRefreshListener{

    lateinit var binding: FragmentFactListBinding

    @Inject
    lateinit var utils: Utils

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    internal lateinit var viewModel: FactListFragmentViewModel

    lateinit var adapter: FactListAdapter

    override fun getViewModel(): FactListFragmentViewModel {
        viewModel = ViewModelProviders.of(this, factory).get(FactListFragmentViewModel::class.java)
        return viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = FactListAdapter(context!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_fact_list, container, false)
        binding.viewModel=viewModel
        binding.fragment=this
        val view: View = binding.root
        initUI()
        return view
    }

    override fun onResume() {
        super.onResume()
    }

    /**
     * @desc method to init the UI elements
     */
    private fun initUI(){
        binding.swipeRefreshLayout.setOnRefreshListener(this)
        val mLayoutManager = LinearLayoutManager(context)
        mLayoutManager.orientation= RecyclerView.VERTICAL
        binding.recyclerFactList.layoutManager=mLayoutManager
        binding.recyclerFactList.itemAnimator= DefaultItemAnimator()
        binding.recyclerFactList.adapter=adapter
        onRefresh()
    }

    override fun onRefresh() {
        getFactsList()
    }

    private fun getFactsList(){
        showSwipeRefreshing()
        viewModel.getFactsList().observe(this, Observer { data->
            dismissSwipeRefresh()
            if(data!=null){
                (activity as MainActivity).updateTitle(data.title!!)
                    if (data.rows?.isNotEmpty()!!) {
                        showNoDataMsg(false, "")
                        refreshAdapter(data?.rows!!)
                    }
                    else{
                        showNoDataMsg(true, "No Facts Found")
                    }
            }
            else{

            }
        })
    }

    private fun refreshAdapter(list: List<FactData>){
        adapter.refreshList(list)
    }

    private fun showNoDataMsg(flag: Boolean, msg: String) {
        if(flag) {
            binding.txtNoData.text=msg
            binding.txtNoData.visibility= View.VISIBLE
            binding.recyclerFactList.visibility= View.INVISIBLE
        } else {
            binding.txtNoData.visibility= View.GONE
            binding.recyclerFactList.visibility= View.VISIBLE
        }
    }

    private fun showSwipeRefreshing(){
        binding.swipeRefreshLayout.isRefreshing=true
    }

    /**
     * @desc method to dismiss refreshing
     */
    private fun dismissSwipeRefresh(){
        if(binding.swipeRefreshLayout.isRefreshing)
            binding.swipeRefreshLayout.isRefreshing=false
    }



}