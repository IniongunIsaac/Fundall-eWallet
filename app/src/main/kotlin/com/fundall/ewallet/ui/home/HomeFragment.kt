package com.fundall.ewallet.ui.home

import android.os.Bundle
import com.fundall.ewallet.BR
import com.fundall.ewallet.R
import com.fundall.ewallet.databinding.HomeFragmentBinding
import com.fundall.ewallet.ui.base.BaseFragment
import javax.inject.Inject


class HomeFragment : BaseFragment<HomeFragmentBinding, HomeViewModel>() {

    @Inject
    lateinit var homeViewModel: HomeViewModel

    private lateinit var binding: HomeFragmentBinding

    override fun getViewModel() = homeViewModel

    override fun getLayoutId() = R.layout.home_fragment

    override fun getBindingVariable() = BR.viewmodel

    override fun getLayoutBinding(binding: HomeFragmentBinding) {
        this.binding = binding
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}
