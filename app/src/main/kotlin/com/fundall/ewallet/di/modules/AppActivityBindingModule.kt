package com.fundall.ewallet.di.modules

import com.fundall.ewallet.di.scopes.PerActivity
import com.fundall.ewallet.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Isaac Iniongun on 2019-09-14.
 * For Fundall eWallet project.
 */

@Module
abstract class AppActivityBindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [AppMainFragmentsBindingModule::class])
    internal abstract fun bindMainActivity(): MainActivity

}