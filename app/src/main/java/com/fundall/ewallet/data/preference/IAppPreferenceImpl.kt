package com.fundall.ewallet.data.preference

import android.content.Context
import com.fundall.ewallet.utils.AppConstants
import com.google.gson.Gson
import javax.inject.Inject

class IAppPreferenceImpl @Inject constructor(private val context: Context, private val gson: Gson) : IAppPreference {

    private val sharedPreference = context.applicationContext.getSharedPreferences(
        AppConstants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    override var authorizationToken: String
        get() = getPreference(AppConstants.AUTHORIZATION_TOKEN, "")
        set(value) = setPreference(AppConstants.AUTHORIZATION_TOKEN, value)

    @Suppress("UNCHECKED_CAST")
    @Synchronized
    fun <T> getPreference(key: String, default: T): T = with(sharedPreference) {
        val res: Any = when (default) {
            is Long -> getLong(key, default)
            is String -> getString(key, default)
            is Int -> getInt(key, default)
            is Boolean -> getBoolean(key, default)
            is Float -> getFloat(key, default)
            else -> throw IllegalArgumentException(AppConstants.CANT_BE_RETRIEVED_FROM_PREFERENCES_MESSAGE)
        }!!

        res as T
    }

    @Synchronized
    fun <T> setPreference(key: String, value: T) = with(sharedPreference.edit()) {
        when (value) {
            is Long -> putLong(key, value)
            is String -> putString(key, value)
            is Int -> putInt(key, value)
            is Boolean -> putBoolean(key, value)
            is Float -> putFloat(key, value)
            else -> throw IllegalArgumentException(AppConstants.CANT_BE_SAVED_TO_PREFERENCES_MESSAGE.replace("value", value.toString(), true))
        }.apply()
    }

}