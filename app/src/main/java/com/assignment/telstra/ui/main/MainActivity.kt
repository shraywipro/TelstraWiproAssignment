package com.assignment.telstra.ui.main

import android.content.Context
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.assignment.telstra.R
import com.assignment.telstra.core.base.BaseActivity
import com.assignment.telstra.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : BaseActivity<MainViewModel>() {

    lateinit var binding: ActivityMainBinding
    private lateinit var context: Context

    @Inject
    internal lateinit var viewModel: MainViewModel

    override fun getViewModel(): MainViewModel {
        return viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        initBinding()
    }

    /**
     * @desc method to init the view binding
     */
    fun initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.mainViewModel = viewModel
        initToolBar()
    }

    private fun initToolBar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.let {
            it.title = "Facts"
            it.setHomeButtonEnabled(true)
            it.setDisplayShowTitleEnabled(true)
        }
    }

    fun updateTitle(title: String) {
        supportActionBar?.let {
            it.title = title
        }
    }

    fun loadFragment(fragment: Fragment?){
        fragment?.let {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.nav_host_fragment, fragment)
                .commit()
        }
    }

}
