package com.fundall.ewallet.ui.register

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fundall.ewallet.data.repository.EWalletRepository
import com.fundall.ewallet.utils.AppResource
import com.fundall.ewallet.utils.isValidEmail
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class RegisterViewModel @Inject constructor(
    private val eWalletRepository: EWalletRepository
) : ViewModel(), CoroutineScope {

    private var job: Job = Job()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _registrationResultStateLiveData.postValue(AppResource.failure(throwable.message ?: "An error occurred."))
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main + coroutineExceptionHandler

    var firstName = ""
    var lastName = ""
    var emailAddress = ""
    var phoneNumber = ""
    var password = ""


    init {
        eWalletRepository.registrationResponse.observeForever {
            if (it.isSuccessful) {
                _registrationResultStateLiveData.postValue(AppResource.success(message = it.response.message))
            } else {
                _registrationResultStateLiveData.postValue(AppResource.failure(it.response.message))
            }
        }
    }

    private val _registrationResultStateLiveData = MutableLiveData<AppResource<Unit>>()
    val registrationResultStateLiveData: LiveData<AppResource<Unit>> = _registrationResultStateLiveData

    fun signup() {

        if (TextUtils.isEmpty(firstName)){
            _registrationResultStateLiveData.postValue(AppResource.failure("First name is required!"))

            return
        }

        if (TextUtils.isEmpty(lastName)){
            _registrationResultStateLiveData.postValue(AppResource.failure("Last name is required!"))

            return
        }

        if (!emailAddress.isValidEmail()){
            _registrationResultStateLiveData.postValue(AppResource.failure("Valid email address is required!"))

            return
        }

        if (TextUtils.isEmpty(phoneNumber)){
            _registrationResultStateLiveData.postValue(AppResource.failure("Phone number is required!"))

            return
        }

        if (TextUtils.isEmpty(password)){
            _registrationResultStateLiveData.postValue(AppResource.failure("Password is required!"))

            return
        }

        _registrationResultStateLiveData.postValue(AppResource.loading())

        launch(Dispatchers.IO) {
            eWalletRepository.registerUser(
                hashMapOf(
                    "firstname" to firstName,
                    "lastname" to lastName,
                    "email" to emailAddress,
                    "password" to password,
                    "password_confirmation" to password
                )
            )
        }

    }

    fun login() {

    }

    fun getInviteCode() { }

    fun termsAndConditions() { }

    fun privacyPolicy() { }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}
