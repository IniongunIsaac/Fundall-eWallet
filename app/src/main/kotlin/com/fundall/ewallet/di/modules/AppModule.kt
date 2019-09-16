package com.fundall.ewallet.di.modules

import android.content.Context
import com.fundall.ewallet.EWalletApplication
import com.fundall.ewallet.di.scopes.AppScope
import com.fundall.ewallet.utils.AppConstants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

/**
 * Created by Isaac Iniongun on 2019-09-14.
 * For Fundall eWallet project.
 */

@Module
class AppModule {

    @Provides
    @AppScope
    internal fun providesGson() = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").serializeNulls() .create()

    @Provides
    @AppScope
    internal fun provideGsonAdapterFactory(gson: Gson) = GsonConverterFactory.create(gson)

    @Provides
    @AppScope
    internal fun provideFile(context: Context) = File (
        context.cacheDir, AppConstants.OKHTTP_CACHE
    )

    @Provides
    @AppScope
    internal fun provideCache(file: File) = Cache (
        file, AppConstants.OKHTTP_CACHE_SIZE.toLong()
    )

    @Provides
    @AppScope
    internal fun bindContext(eWalletApplication: EWalletApplication): Context = eWalletApplication

}