package com.fundall.ewallet.di.modules

import com.fundall.ewallet.di.scopes.PerFragment
import com.fundall.ewallet.ui.home.HomeFragment
import com.fundall.ewallet.ui.login.LoginFragment
import com.fundall.ewallet.ui.register.RegisterFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Isaac Iniongun on 2019-09-16.
 * For Fundall eWallet project.
 */

@Module
abstract class AppMainFragmentsBindingModule {

    @PerFragment
    @ContributesAndroidInjector
    internal abstract fun bindRegisterFragment(): RegisterFragment

    @PerFragment
    @ContributesAndroidInjector
    internal abstract fun bindLoginFragment(): LoginFragment

    @PerFragment
    @ContributesAndroidInjector
    internal abstract fun bindHomeFragment(): HomeFragment

}