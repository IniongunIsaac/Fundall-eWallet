package com.fundall.ewallet.ui.main

import com.fundall.ewallet.BR
import com.fundall.ewallet.R
import com.fundall.ewallet.databinding.ActivityMainBinding
import com.fundall.ewallet.ui.base.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    @Inject
    lateinit var mainViewModel: MainViewModel

    private lateinit var binding: ActivityMainBinding

    override fun getLayoutId() = R.layout.activity_main

    override fun getViewModel() = mainViewModel

    override fun getBindingVariable() = BR.viewmodel

    override fun getBinding(binding: ActivityMainBinding) {
        this.binding = binding
    }


}
