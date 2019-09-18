package com.fundall.ewallet.ui.home

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.fundall.ewallet.BR
import com.fundall.ewallet.R
import com.fundall.ewallet.data.models.UserData
import com.fundall.ewallet.databinding.HomeFragmentBinding
import com.fundall.ewallet.ui.base.BaseFragment
import com.fundall.ewallet.utils.AppResultState
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.home_fragment.*
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

        homeViewModel.userDataResultStateLiveData.observe(this, Observer { userDataAppResource ->
            when(userDataAppResource.state) {

                AppResultState.SUCCESS -> {
                    dismissLoadingDialog()
                    userDataAppResource.data?.let {
                        showUserData(it)
                    }

                }

                AppResultState.LOADING -> {
                    showLoadingDialog()
                }

                AppResultState.FAILURE -> {
                    dismissLoadingDialog()
                    showMessageSnackBar(binding.root, userDataAppResource.message!!, true, duration = Snackbar.LENGTH_LONG)
                }

            }
        })

        logout_imageView.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
        }

    }

    private fun showUserData(userData: UserData) {

        total_balance_value_textview.text = getString(R.string.amount_placeholder, userData.monthly_target)
        income_textView.text = getString(R.string.amount_placeholder, userData.monthly_target)
        spent_textView.text = getString(R.string.amount_placeholder, userData.monthly_target)

        user_imageView.setImageURI(userData.avatar)

    }

}
