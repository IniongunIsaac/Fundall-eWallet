package com.fundall.ewallet.di.modules

import com.fundall.ewallet.data.preference.IAppPreference
import com.fundall.ewallet.data.preference.IAppPreferenceImpl
import com.fundall.ewallet.di.scopes.AppScope
import dagger.Binds
import dagger.Module

/**
 * Created by Isaac Iniongun on 2019-09-14.
 * For Fundall eWallet project.
 */

@Module
abstract class PreferenceModule {

    @Binds
    @AppScope
    internal abstract fun bindAppPreference(
        preference: IAppPreferenceImpl
    ): IAppPreference

}