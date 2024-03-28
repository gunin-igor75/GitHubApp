package com.github.gunin_igor75.githubapp.domain.model

data class UserDetails(
    val name: String,
    val avatar: String?,
    val email: String?,
    val organization: String?,
    val following: Int,
    val followers: Int,
    val createdAt: String
)
