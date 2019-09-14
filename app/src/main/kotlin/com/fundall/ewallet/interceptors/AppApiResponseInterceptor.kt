package com.fundall.ewallet.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import org.json.JSONObject
import javax.inject.Inject

/**
 * Created by Isaac Iniongun on 2019-09-14.
 * For Fundall eWallet project.
 */

class AppApiResponseInterceptor @Inject constructor(): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val response = chain.proceed(chain.request())
        val responseBody = JSONObject(response.body()!!.string())

        val newResponseBody = if (response.isSuccessful) {

            //Successful response from the server
            JSONObject().apply {
                put("isSuccessful", true)
                put("success", responseBody.getJSONObject("success"))
            }

        } else {

            JSONObject().apply {
                put("isSuccessful", false)
                put("error", responseBody.getJSONObject("error"))
            }
        }
        return response.newBuilder().body(
            ResponseBody.create(response.body()!!.contentType(), newResponseBody.toString())
        ).build()

    }
}