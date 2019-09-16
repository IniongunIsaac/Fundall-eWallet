package com.fundall.ewallet.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fundall.ewallet.data.models.BaseResponse
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(
    private val eWalletApiService: EWalletApiService
) : NetworkDataSource {

    private val _registrationResponse = MutableLiveData<BaseResponse<Nothing>>()
    override val registrationResponse: LiveData<BaseResponse<Nothing>>
        get() = _registrationResponse

    override suspend fun registerUser(data: HashMap<String, String>) {
        val regRes = eWalletApiService.registerUserAsync(data).await()
        _registrationResponse.postValue(regRes)
    }
}