package com.github.gunin_igor75.githubapp.data.network.api

import com.github.gunin_igor75.githubapp.data.network.dto.UserDetailsDto
import com.github.gunin_igor75.githubapp.data.network.dto.UserDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    suspend fun loadUsers(
        @Query("since") offset: Int,
        @Query("per_page") limit: Int
    ): List<UserDto>

    @GET("users/{name}")
    suspend fun loadUser(
        @Path("name") name: String
    ): UserDetailsDto
}