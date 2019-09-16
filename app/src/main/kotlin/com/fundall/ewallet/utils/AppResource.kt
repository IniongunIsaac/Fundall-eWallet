package com.fundall.ewallet.utils

/**
 * Created by Isaac Iniongun on 2019-09-15.
 * For Fundall eWallet project.
 */

class AppResource <out D> constructor(
    val state: AppResultState,
    val message: String? = null,
    val data: D? = null
){
    companion object {
        @JvmStatic
        fun <D> success(
            data: D? = null,
            message: String? = null
        ): AppResource<D> = AppResource(
            state = AppResultState.SUCCESS,
            data = data,
            message = message
        )

        @JvmStatic
        fun <D> failure(
            message: String? = null
        ): AppResource<D> = AppResource(
            state = AppResultState.FAILURE,
            message = message
        )

        @JvmStatic
        fun <D> loading(): AppResource<D> = AppResource(
            state = AppResultState.LOADING,
            data = null,
            message = null
        )

    }
}