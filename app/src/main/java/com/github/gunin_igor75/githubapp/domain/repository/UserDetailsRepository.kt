package com.github.gunin_igor75.githubapp.domain.repository

import com.github.gunin_igor75.githubapp.domain.model.UserDetailsState

interface UserDetailsRepository {

    suspend fun getUser(name: String): UserDetailsState
}