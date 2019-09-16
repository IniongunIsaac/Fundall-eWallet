package com.fundall.ewallet.data.repository

import androidx.lifecycle.LiveData
import com.fundall.ewallet.data.models.BaseResponse

/**
 * Created by Isaac Iniongun on 2019-09-15.
 * For Fundall eWallet project.
 */

interface EWalletRepository {

    val registrationResponse: LiveData<BaseResponse<Nothing>>

    suspend fun registerUser(data: HashMap<String, String>)
}