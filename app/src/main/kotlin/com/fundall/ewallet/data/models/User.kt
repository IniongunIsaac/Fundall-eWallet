package com.fundall.ewallet.data.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("access_token") val accessToken: String,
    val avatar: String,
    @SerializedName("created_at") val createdAt: String,
    val email: String,
    @SerializedName("expires_at") val expiresAt: String,
    @SerializedName("firstname") val firstName: String,
    val id: Int,
    @SerializedName("lastname") val lastName: String,
    @SerializedName("monthly_target") val monthlyTarget: Int,
    @SerializedName("token_type") val tokenType: String,
    @SerializedName("updated_at") val updatedAt: String
)