package com.fundall.ewallet.di.components

import android.content.Context
import com.fundall.ewallet.EWalletApplication
import com.fundall.ewallet.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

/**
 * Created by Isaac Iniongun on 2019-09-14.
 * For Fundall eWallet project.
 */

@Component(
    modules = [
    AndroidInjectionModule::class,
    NetworkModule::class,
    AppViewModelsModule::class,
    AppUIBindingModule::class,
    AppModule::class,
    PreferenceModule::class
    ]
)
interface AppComponent: AndroidInjector<EWalletApplication> {

    fun inject(context: Context)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bindEWalletApplicationInstance(context: EWalletApplication): Builder

        fun buildAppComponent(): AppComponent
    }

}