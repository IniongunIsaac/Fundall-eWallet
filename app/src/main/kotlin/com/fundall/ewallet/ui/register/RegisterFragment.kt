package com.fundall.ewallet.ui.register

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.fundall.ewallet.BR
import com.fundall.ewallet.R
import com.fundall.ewallet.databinding.RegisterFragmentBinding
import com.fundall.ewallet.ui.base.BaseFragment
import com.fundall.ewallet.utils.AppResultState
import com.fundall.ewallet.utils.MainNavigationConstants
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject


class RegisterFragment : BaseFragment<RegisterFragmentBinding, RegisterViewModel>() {

    @Inject
    lateinit var registerViewModel: RegisterViewModel

    private lateinit var binding: RegisterFragmentBinding

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        registerViewModel.registrationResultStateLiveData.observe(this, Observer {
            when(it.state) {

                AppResultState.SUCCESS -> {
                    dismissLoadingDialog()
                    showMessageSnackBar(binding.root, it.message!!, false, duration = Snackbar.LENGTH_LONG)
                    //navigate to login fragment
                    navigateToLogin()
                }

                AppResultState.LOADING -> {
                    showLoadingDialog()
                }

                AppResultState.FAILURE -> {
                    dismissLoadingDialog()
                    showMessageSnackBar(binding.root, it.message!!, true, duration = Snackbar.LENGTH_LONG)
                }

            }
        })

        registerViewModel.mainNavigationLiveData.observe(this, Observer {
            when(it) {
                MainNavigationConstants.LOGIN -> navigateToLogin()
            }
        })

    }

    private fun navigateToLogin() {
        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
    }

    override fun getViewModel() = registerViewModel

    override fun getLayoutId() = R.layout.register_fragment

    override fun getBindingVariable() = BR.viewmodel

    override fun getLayoutBinding(binding: RegisterFragmentBinding) {
        this.binding = binding
    }
}
