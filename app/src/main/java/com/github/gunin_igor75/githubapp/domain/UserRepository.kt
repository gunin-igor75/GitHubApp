package com.github.gunin_igor75.githubapp.domain

import androidx.paging.PagingData
import com.github.gunin_igor75.githubapp.domain.model.User
import com.github.gunin_igor75.githubapp.domain.model.UserDetails
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUsers(): Flow<PagingData<User>>

    suspend fun getUser(name: String): UserDetails
}