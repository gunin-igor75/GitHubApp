package com.github.gunin_igor75.githubapp.data.network.dto

import com.google.gson.annotations.SerializedName

data class UserDetailsDto(
    @SerializedName("name") val name: String,
    @SerializedName("avatar_url") val avatar: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("company") val organization: String?,
    @SerializedName("following") val following: Int,
    @SerializedName("followers") val followers: Int,
    @SerializedName("created_at") val createdAt: String
)
