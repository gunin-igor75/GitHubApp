package com.github.gunin_igor75.githubapp.domain.model

import androidx.compose.runtime.Immutable

@Immutable
data class UserDetails(
    val name: String,
    val avatar: String?,
    val email: String?,
    val organization: String?,
    val following: Int,
    val followers: Int,
    val createdAt: String,
) {
    companion object {
        val USER_DETAILS = UserDetails(
            name = "User Userock",
            avatar = null,
            email = "user@mail.ru",
            organization = "IT company, IT company, IT company",
            following = 10,
            followers = 55,
            createdAt = "25 марта 2024"
        )
    }
}
