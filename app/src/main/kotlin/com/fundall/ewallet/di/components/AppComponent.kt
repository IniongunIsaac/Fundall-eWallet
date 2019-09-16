package com.fundall.ewallet.di.components

import com.fundall.ewallet.EWalletApplication
import com.fundall.ewallet.di.modules.*
import com.fundall.ewallet.di.scopes.AppScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

/**
 * Created by Isaac Iniongun on 2019-09-14.
 * For Fundall eWallet project.
 */

@Component(
    modules = [
    AndroidInjectionModule::class,
    NetworkModule::class,
    AppViewModelsModule::class,
    AppActivityBindingModule::class,
    AppModule::class,
    PreferenceModule::class
    ]
)
@AppScope
interface AppComponent: AndroidInjector<DaggerApplication> {

    fun inject(eWalletApplication: EWalletApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bindEWalletApplicationInstance(eWalletApplication: EWalletApplication): Builder

        fun buildAppComponent(): AppComponent
    }

}