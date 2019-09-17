package com.fundall.ewallet.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

/**
 * Created by Isaac Iniongun on 2019-09-17.
 * For Fundall eWallet project.
 */

abstract class BaseViewModel: ViewModel(), CoroutineScope {

    private var job: Job = Job()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        handleCoroutineException(throwable.message ?: "An error occurred.")
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main + coroutineExceptionHandler

    abstract fun handleCoroutineException(errorMessage: String)

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}