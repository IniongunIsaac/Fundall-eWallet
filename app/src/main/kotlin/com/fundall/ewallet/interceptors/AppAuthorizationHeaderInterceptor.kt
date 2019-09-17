package com.fundall.ewallet.interceptors

import com.fundall.ewallet.data.preference.IAppPreference
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * Created by Isaac Iniongun on 2019-09-14.
 * For Fundall eWallet project.
 */

class AppAuthorizationHeaderInterceptor @Inject constructor (
    private val appPreference: IAppPreference
): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = appPreference.authorizationToken.let {
            val request = chain.request()
            request.newBuilder()
                .addHeader("Content-Type", "Application/Json")
                .addHeader(
                "Authorization", "Bearer $it"
            ).build()
        } ?: chain.request()

        return chain.proceed(newRequest)
    }
}