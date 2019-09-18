package com.fundall.ewallet

import com.facebook.drawee.backends.pipeline.Fresco
import com.fundall.ewallet.di.components.AppComponent
import com.fundall.ewallet.di.components.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

/**
 * Created by Isaac Iniongun on 2019-09-13.
 * For Fundall eWallet project.
 */

class EWalletApplication: DaggerApplication() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        Fresco.initialize(this)

        appComponent.inject(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {

        appComponent = DaggerAppComponent.builder()
            .bindEWalletApplicationInstance(this)
            .buildAppComponent()

        return appComponent
    }
}