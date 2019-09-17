package com.fundall.ewallet.ui.signin

import android.os.Bundle
import androidx.lifecycle.Observer
import com.fundall.ewallet.BR
import com.fundall.ewallet.R
import com.fundall.ewallet.databinding.SignInFragmentBinding
import com.fundall.ewallet.ui.base.BaseFragment
import com.fundall.ewallet.utils.AppResultState
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject


class SignInFragment : BaseFragment<SignInFragmentBinding, SignInViewModel>() {

    @Inject
    lateinit var signInViewModel: SignInViewModel

    private lateinit var binding: SignInFragmentBinding

    override fun getViewModel() = signInViewModel

    override fun getLayoutId() = R.layout.sign_in_fragment

    override fun getBindingVariable() = BR.viewmodel

    override fun getLayoutBinding(binding: SignInFragmentBinding) {
        this.binding = binding
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        signInViewModel.loginResultStateLiveData.observe(this, Observer {
            when(it.state) {

                AppResultState.SUCCESS -> {
                    dismissLoadingDialog()
                    showMessageSnackBar(binding.root, it.message!!, false, duration = Snackbar.LENGTH_LONG)
                    //navigate to home fragment
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

    }

}
