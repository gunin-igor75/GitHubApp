package com.github.gunin_igor75.githubapp.domain.model

import androidx.compose.runtime.Immutable

@Immutable
data class User(
    val id: Long,
    val title: String,
    val avatar: String?,
) {
    companion object {
        val USER_DEFAULT =
            User(
                id = 12554L,
                title = "igor-gunin75",
                avatar = null
            )
    }
}

