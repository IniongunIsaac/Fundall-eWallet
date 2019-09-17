package com.fundall.ewallet.ui.base

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.content.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.fundall.ewallet.R
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

/**
 * Created by Isaac Iniongun on 2019-09-15.
 * For Fundall eWallet project.
 */

abstract class BaseActivity<in DB: ViewDataBinding, out VM: BaseViewModel>: DaggerAppCompatActivity(), CoroutineScope {

    private lateinit var job: Job

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        showMessageSnackBar(view = this.currentFocus!!, message = throwable.message ?: "An error occurred.", isError = true)
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main + coroutineExceptionHandler

    private lateinit var dialog: AlertDialog

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getViewModel(): VM

    abstract fun getBindingVariable(): Int

    abstract fun getBinding(binding: DB)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeDataBinding()

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)

        createDialog()

        job = Job()
    }

    override fun onDestroy() {
        super.onDestroy()

        job.cancel()
    }

    private fun initializeDataBinding() {
        val binding: DB = DataBindingUtil.setContentView(this, getLayoutId())

        binding.apply {
            setVariable(getBindingVariable(), getViewModel())
            executePendingBindings()
        }

        getBinding(binding)
    }

    private fun createDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setView(R.layout.layout_progress_dialog)
        builder.setCancelable(false)

        dialog = builder.create()
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    fun hideKeyboard(activity: Activity) {
        val imm = getSystemService<InputMethodManager>()
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm!!.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun showMessageSnackBar(view: View, message: String, isError: Boolean = false, duration: Int = Snackbar.LENGTH_SHORT){
        val snackBar = Snackbar.make(view, message, duration)
        val param = snackBar.view.layoutParams as FrameLayout.LayoutParams
        val snackBarLayout = snackBar.view as Snackbar.SnackbarLayout
        if (isError) snackBarLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.red)) else snackBarLayout.setBackgroundColor(
            ContextCompat.getColor(this, R.color.colorPrimary))
        snackBarLayout.findViewById<TextView>(com.google.android.material.R.id.snackbar_text).setTextColor(
            ContextCompat.getColor(this, R.color.white)
        )

        param.gravity = Gravity.TOP

        snackBar.view.layoutParams = param

        snackBar.show()
    }

    fun showLoadingDialog() {
        hideKeyboard(this)
        dialog.show()
    }

    fun dismissLoadingDialog() {
        if (dialog.isShowing) dialog.dismiss()
    }

}