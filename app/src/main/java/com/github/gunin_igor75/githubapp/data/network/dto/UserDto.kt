package com.github.gunin_igor75.githubapp.data.network.dto

import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("id") val id: Long,
    @SerializedName("login") val title: String,
    @SerializedName("avatar_url") val avatar: String?
)
