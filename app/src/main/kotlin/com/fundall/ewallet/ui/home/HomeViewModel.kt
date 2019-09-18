package com.fundall.ewallet.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fundall.ewallet.data.models.UserData
import com.fundall.ewallet.data.repository.EWalletRepository
import com.fundall.ewallet.ui.base.BaseViewModel
import com.fundall.ewallet.utils.AppResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val eWalletRepository: EWalletRepository
    ) : BaseViewModel() {


    private val _userDataResultStateLiveData = MutableLiveData<AppResource<UserData>>()
    val userDataResultStateLiveData: LiveData<AppResource<UserData>> = _userDataResultStateLiveData

    init {

        _userDataResultStateLiveData.postValue(AppResource.loading())

        launch(Dispatchers.IO) {
            eWalletRepository.getUserData()
        }

        eWalletRepository.userDataResponse.observeForever {
            if (it.isSuccessful) {
                _userDataResultStateLiveData.postValue(AppResource.success(data = it.response.data, message = "User profile loaded."))

            } else {
                _userDataResultStateLiveData.postValue(AppResource.failure(it.response.message))
            }
        }
    }

    override fun handleCoroutineException(errorMessage: String) {
        _userDataResultStateLiveData.postValue(AppResource.failure(errorMessage))
    }
}
