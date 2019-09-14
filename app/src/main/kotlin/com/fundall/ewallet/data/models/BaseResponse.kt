package com.fundall.ewallet.data.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Isaac Iniongun on 2019-09-14.
 * For Fundall eWallet project.
 */

data class BaseResponse<T>(
    val isSuccessful: Boolean,
    @SerializedName("success", alternate = ["error"]) val response: ResponseModel<T>
)