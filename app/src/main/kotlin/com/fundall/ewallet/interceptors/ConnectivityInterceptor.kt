package com.fundall.ewallet.interceptors

import android.content.Context
import android.net.ConnectivityManager
import com.fundall.ewallet.exceptions.NoConnectivityException
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * Created by Isaac Iniongun on 2019-09-15.
 * For Fundall eWallet project.
 */

class ConnectivityInterceptor @Inject constructor(private val context: Context): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isOnline())
            throw NoConnectivityException()
        return chain.proceed(chain.request())
    }

    private fun isOnline(): Boolean {
        val connectivityManager = context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

}