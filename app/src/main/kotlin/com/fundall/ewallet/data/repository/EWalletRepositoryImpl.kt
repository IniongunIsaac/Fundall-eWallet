package com.fundall.ewallet.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fundall.ewallet.data.models.BaseResponse
import com.fundall.ewallet.data.network.EWalletApiService
import javax.inject.Inject

class EWalletRepositoryImpl @Inject constructor(
    private val eWalletApiService: EWalletApiService
) : EWalletRepository {

    private val _registrationResponse = MutableLiveData<BaseResponse<Nothing>>()
    override val registrationResponse: LiveData<BaseResponse<Nothing>>
        get() = _registrationResponse

    override suspend fun registerUser(data: HashMap<String, String>) {
        val regRes = eWalletApiService.registerUserAsync(data).await()
        _registrationResponse.postValue(regRes)
    }
}