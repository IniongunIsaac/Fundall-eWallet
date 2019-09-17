package com.fundall.ewallet.di.modules

import androidx.lifecycle.ViewModel
import com.fundall.ewallet.di.keys.AppViewModelKey
import com.fundall.ewallet.di.scopes.AppScope
import com.fundall.ewallet.ui.home.HomeViewModel
import com.fundall.ewallet.ui.login.LoginViewModel
import com.fundall.ewallet.ui.main.MainViewModel
import com.fundall.ewallet.ui.register.RegisterViewModel
import com.fundall.ewallet.ui.signin.SignInViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Isaac Iniongun on 2019-09-14.
 * For Fundall eWallet project.
 */

@Module
abstract class AppViewModelsModule {

    @Binds
    @IntoMap
    @AppScope
    @AppViewModelKey(MainViewModel::class)
    internal abstract fun bindMainViewModel(
        viewModel: MainViewModel
    ): ViewModel

    @Binds
    @IntoMap
    @AppScope
    @AppViewModelKey(LoginViewModel::class)
    internal abstract fun bindLoginViewModel(
        viewModel: LoginViewModel
    ): ViewModel

    @Binds
    @IntoMap
    @AppScope
    @AppViewModelKey(RegisterViewModel::class)
    internal abstract fun bindRegisterViewModel(
        viewModel: RegisterViewModel
    ): ViewModel

    @Binds
    @IntoMap
    @AppScope
    @AppViewModelKey(HomeViewModel::class)
    internal abstract fun bindHomeViewModel(
        viewModel: HomeViewModel
    ): ViewModel

    @Binds
    @IntoMap
    @AppScope
    @AppViewModelKey(SignInViewModel::class)
    internal abstract fun bindSignInViewModel(
        viewModel: SignInViewModel
    ): ViewModel

}