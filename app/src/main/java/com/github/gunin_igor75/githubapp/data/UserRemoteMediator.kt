package com.github.gunin_igor75.githubapp.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.github.gunin_igor75.githubapp.data.local.db.UserDao
import com.github.gunin_igor75.githubapp.data.local.model.UserEntity
import com.github.gunin_igor75.githubapp.data.mappers.toUserEntity
import com.github.gunin_igor75.githubapp.data.network.api.ApiService
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class UserRemoteMediator @Inject constructor(
    private val userDao: UserDao,
    private val apiService: ApiService,
) : RemoteMediator<Int, UserEntity>() {

    private var pageIndex = 0

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UserEntity>,
    ): MediatorResult {

        pageIndex =
            getPageIndex(loadType) ?: return MediatorResult.Success(endOfPaginationReached = true)

        val limit = state.config.pageSize
        val offset = pageIndex * limit

        return try {
            val users = fetchUsers(offset, limit)
            if (loadType == LoadType.REFRESH) {
                userDao.refresh(users)
            } else {
                userDao.insert(users)
            }
            MediatorResult.Success(
                endOfPaginationReached = users.size < limit
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

    private fun getPageIndex(loadType: LoadType): Int? {
        pageIndex = when (loadType) {
            LoadType.REFRESH -> 0
            LoadType.PREPEND -> return null
            LoadType.APPEND -> ++pageIndex
        }
        return pageIndex
    }

    private suspend fun fetchUsers(
        offset: Int,
        limit: Int,
    ): List<UserEntity> = apiService.loadUsers(offset, limit)
        .map { it.toUserEntity() }
}