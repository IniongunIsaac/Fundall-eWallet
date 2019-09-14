package com.fundall.ewallet.utils

/**
 * Created by Isaac Iniongun on 2019-09-14.
 * For Fundall eWallet project.
 */

object AppConstants {

    const val OKHTTP_CACHE = "App Cache"
    const val OKHTTP_CACHE_SIZE = 10 * 1000 * 1000

    const val SHARED_PREFERENCES_NAME =  "default"
    const val CANT_BE_RETRIEVED_FROM_PREFERENCES_MESSAGE = "This type can't be retrieved from Preferences"
    const val CANT_BE_SAVED_TO_PREFERENCES_MESSAGE = "This type value can't be saved into Preferences"

    const val AUTHORIZATION_TOKEN = "Authorization Token"

    object URLS {
        const val REGISTER = "register"
        const val LOGIN = "login"
        const val GET_USER_DATA = "base/profile"
        const val UPDATE_AVATAR = "base/avatar"
    }

    const val USER_DETAILS = "User Details"
    const val USER_DATA_DETAILS = "User Data Details"
    const val HAS_SAVED_USER_DETAILS = "Has Saved User Details"
    const val HAS_SAVED_USER_DATA_DETAILS = "Has Saved User Data Details"
}