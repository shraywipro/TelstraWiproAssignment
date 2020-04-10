package com.assignment.telstra.ui.splash

import android.content.Context
import android.content.Intent

import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import com.assignment.telstra.R
import com.assignment.telstra.core.base.BaseActivity
import com.assignment.telstra.databinding.ActivitySplashBinding
import com.assignment.telstra.ui.main.MainActivity

import javax.inject.Inject

class SplashActivity : BaseActivity<SplashViewModel>() {

    lateinit var binding: ActivitySplashBinding

    private lateinit var context: Context
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable


    @Inject
    internal lateinit var viewModel: SplashViewModel

    override fun getViewModel(): SplashViewModel {
        return viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        initBinding()
    }

    private fun initBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        binding.viewModel=viewModel
        init()
    }


    /**
     * @desc method to start handler for 2.5 seconds to hold on Splash screen and then navigate to MainActivity
     */
    private fun init() {
        handler= Handler()
        runnable = Runnable {
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }
        handler.postDelayed(runnable, 2500)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable)
    }
}
