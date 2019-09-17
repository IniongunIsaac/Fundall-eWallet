package com.fundall.ewallet.ui.login

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.fundall.ewallet.BR
import com.fundall.ewallet.R
import com.fundall.ewallet.databinding.LoginFragmentBinding
import com.fundall.ewallet.ui.base.BaseFragment
import kotlinx.android.synthetic.main.login_fragment.*
import javax.inject.Inject


class LoginFragment : BaseFragment<LoginFragmentBinding, LoginViewModel>() {

    @Inject
    lateinit var loginViewModel: LoginViewModel

    private lateinit var binding: LoginFragmentBinding

    override fun getViewModel() = loginViewModel

    override fun getLayoutId() = R.layout.login_fragment

    override fun getBindingVariable() = BR.viewmodel

    override fun getLayoutBinding(binding: LoginFragmentBinding) {
        this.binding = binding
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        password_background_imageView.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signInFragment)
        }

        create_account_button.setOnClickListener {
            findNavController().navigate(R.id.registerFragment)
        }

    }

}
