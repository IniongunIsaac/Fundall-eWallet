package com.fundall.ewallet.data.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Isaac Iniongun on 2019-09-14.
 * For Fundall eWallet project.
 */

data class ResponseModel<T> (
    val message: String?,
    val code: String?,
    val status: String?,
    @SerializedName("data", alternate = ["user"]) val data: T?
)