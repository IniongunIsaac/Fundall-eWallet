package com.fundall.ewallet.data.service

import com.fundall.ewallet.data.models.BaseResponse
import com.fundall.ewallet.data.models.User
import com.fundall.ewallet.data.models.UserData
import com.fundall.ewallet.utils.AppConstants
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by Isaac Iniongun on 2019-09-14.
 * For Fundall eWallet project.
 */

interface EWalletApiService {

    @POST(AppConstants.URLS.REGISTER)
    fun registerUser(@Body requestBody: HashMap<String, String>): Deferred<BaseResponse<Nothing>>

    @POST(AppConstants.URLS.LOGIN)
    fun authenticateUser(@Body requestBody: HashMap<String, String>): Deferred<BaseResponse<User>>

    @GET(AppConstants.URLS.GET_USER_DATA)
    fun getUserData(): Deferred<BaseResponse<UserData>>

    @POST(AppConstants.URLS.UPDATE_AVATAR)
    fun updateAvatar(@Body requestBody: HashMap<String, String>)

}