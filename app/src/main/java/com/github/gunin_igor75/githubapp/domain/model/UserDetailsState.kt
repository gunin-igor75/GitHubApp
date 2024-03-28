package com.github.gunin_igor75.githubapp.domain.model

sealed interface UserDetailsState {
    data object Initial : UserDetailsState
    data object Loading : UserDetailsState
    data class Error(
        val throwable: Throwable,
    ) : UserDetailsState

    data class Success(
        val userDetails: UserDetails,
    ) : UserDetailsState
}
