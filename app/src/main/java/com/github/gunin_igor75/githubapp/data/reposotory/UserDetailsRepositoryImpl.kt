package com.github.gunin_igor75.githubapp.data.reposotory

import com.github.gunin_igor75.githubapp.data.mappers.toUserDetails
import com.github.gunin_igor75.githubapp.data.network.api.ApiService
import com.github.gunin_igor75.githubapp.domain.model.UserDetailsState
import com.github.gunin_igor75.githubapp.domain.repository.UserDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class UserDetailsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : UserDetailsRepository {

    override suspend fun getUser(name: String): UserDetailsState {
        return withContext(Dispatchers.IO) {
            try {
                val useDetailsDto = apiService.loadUser(name)
                UserDetailsState.Success(useDetailsDto.toUserDetails())
            } catch (e: IOException) {
                UserDetailsState.Error(e)
            } catch (e: HttpException) {
                UserDetailsState.Error(e)
            }
        }
    }
}