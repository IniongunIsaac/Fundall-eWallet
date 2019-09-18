package com.fundall.ewallet.data.repository

import androidx.lifecycle.LiveData
import com.fundall.ewallet.data.models.BaseResponse
import com.fundall.ewallet.data.models.User
import com.fundall.ewallet.data.models.UserData

/**
 * Created by Isaac Iniongun on 2019-09-15.
 * For Fundall eWallet project.
 */

interface EWalletRepository {

    val registrationResponse: LiveData<BaseResponse<Nothing>>

    suspend fun registerUser(data: HashMap<String, String>)

    val loginResponse: LiveData<BaseResponse<User>>

    suspend fun authenticateUser(data: HashMap<String, String>)

    val userDataResponse: LiveData<BaseResponse<UserData>>

    suspend fun getUserData()
}