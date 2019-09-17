package com.fundall.ewallet.ui.signin

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fundall.ewallet.data.models.User
import com.fundall.ewallet.data.preference.IAppPreference
import com.fundall.ewallet.data.repository.EWalletRepository
import com.fundall.ewallet.ui.base.BaseViewModel
import com.fundall.ewallet.utils.AppResource
import com.fundall.ewallet.utils.isValidEmail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignInViewModel @Inject constructor(
    private val eWalletRepository: EWalletRepository,
    private val appPreference: IAppPreference
) : BaseViewModel() {

    var emailAddress = ""
    var password = ""

    private val _loginResultStateLiveData = MutableLiveData<AppResource<User>>()
    val loginResultStateLiveData: LiveData<AppResource<User>> = _loginResultStateLiveData

    init {
        eWalletRepository.loginResponse.observeForever {
            if (it.isSuccessful) {
                //Save user details in shared preferences
                appPreference.userDetails = it.response.data!!
                //save authToken in shared preferences
                appPreference.authorizationToken = it.response.data.accessToken

                _loginResultStateLiveData.postValue(AppResource.success(data = it.response.data, message = "Sign in success."))

            } else {
                _loginResultStateLiveData.postValue(AppResource.failure(it.response.message))
            }
        }
    }

    fun signin() {

        if (!emailAddress.isValidEmail()){
            _loginResultStateLiveData.postValue(AppResource.failure("Valid email address is required!"))

            return
        }
        if (TextUtils.isEmpty(password)){
            _loginResultStateLiveData.postValue(AppResource.failure("Password is required!"))

            return
        }

        _loginResultStateLiveData.postValue(AppResource.loading())

        launch(Dispatchers.IO) {
            eWalletRepository.authenticateUser(
                hashMapOf(
                    "email" to emailAddress,
                    "password" to password
                )
            )
        }

    }

    override fun handleCoroutineException(errorMessage: String) {
        _loginResultStateLiveData.postValue(AppResource.failure(errorMessage))
    }
}
