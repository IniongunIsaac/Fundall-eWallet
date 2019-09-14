package com.fundall.ewallet.di.modules

import com.fundall.ewallet.BuildConfig
import com.fundall.ewallet.data.service.EWalletApiService
import com.fundall.ewallet.di.scopes.AppScope
import com.fundall.ewallet.interceptors.AppApiResponseInterceptor
import com.fundall.ewallet.interceptors.AppAuthorizationHeaderInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

/**
 * Created by Isaac Iniongun on 2019-09-14.
 * For Fundall eWallet project.
 */

@Module
class NetworkModule {

    @Provides
    @AppScope
    internal fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor (
        HttpLoggingInterceptor.Logger { log ->
            Timber.e(log)
        }
    ).apply {
        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
        else HttpLoggingInterceptor.Level.NONE
    }

    @Provides
    @AppScope
    internal fun provideOkhttpClient(
        cache: Cache,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        authHeaderInterceptor: AppAuthorizationHeaderInterceptor,
        apiResponseInterceptor: AppApiResponseInterceptor
        ) = OkHttpClient.Builder().apply {
            addInterceptor(httpLoggingInterceptor)
            addInterceptor(authHeaderInterceptor)
            addInterceptor(apiResponseInterceptor)
            cache(cache)
            followRedirects(true)
            followSslRedirects(true)
            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
        }.build()

    @Provides
    @AppScope
    internal fun provideRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory
    ) = Retrofit.Builder().apply {
        client(okHttpClient)
        addCallAdapterFactory(CoroutineCallAdapterFactory())
        addConverterFactory(converterFactory)
        baseUrl(BuildConfig.BASE_URL)
    }.build()

    @Provides
    @AppScope
    internal fun provideEWalletApiService(retrofit: Retrofit) = retrofit.create(EWalletApiService::class.java)

}