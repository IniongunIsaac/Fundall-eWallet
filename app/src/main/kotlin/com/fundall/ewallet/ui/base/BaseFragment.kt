package com.fundall.ewallet.ui.base

import android.os.Bundle
import android.os.IBinder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.core.content.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

/**
 * Created by Isaac Iniongun on 2019-09-15.
 * For Fundall eWallet project.
 */

abstract class BaseFragment<in DB : ViewDataBinding, out WM : BaseViewModel> : DaggerFragment(), CoroutineScope {

    private lateinit var job: Job

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        showMessageSnackBar(view = this.view!!, message = throwable.message ?: "An error occurred.", isError = true)
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main + coroutineExceptionHandler

    abstract fun getViewModel(): WM

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getBindingVariable(): Int

    abstract fun getLayoutBinding(binding: DB)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: DB = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)

        binding.apply {
            setVariable(getBindingVariable(), getViewModel())
            executePendingBindings()
            lifecycleOwner = this@BaseFragment
        }

        getLayoutBinding(binding)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        job = Job()
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    fun hideKeyBoard(token: IBinder) {
        val inputMethodManager = activity?.getSystemService<InputMethodManager>()
        inputMethodManager?.hideSoftInputFromWindow(token, 0)
    }

    fun showKeyBoard() {
        val inputMethodManager = activity?.getSystemService<InputMethodManager>()
        inputMethodManager?.toggleSoftInput(
            InputMethodManager.SHOW_FORCED,
            InputMethodManager.HIDE_IMPLICIT_ONLY
        )
    }

    fun showMessageSnackBar(view: View, message: String, isError: Boolean = false, duration: Int = Snackbar.LENGTH_SHORT) =
        (activity as BaseActivity<*, *>).showMessageSnackBar(view, message, isError, duration)

    fun showLoadingDialog() = (activity as BaseActivity<*, *>).showLoadingDialog()

    fun dismissLoadingDialog() = (activity as BaseActivity<*, *>).dismissLoadingDialog()
}