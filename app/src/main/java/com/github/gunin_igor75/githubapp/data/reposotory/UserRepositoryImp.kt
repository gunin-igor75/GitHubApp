package com.github.gunin_igor75.githubapp.data.reposotory

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.github.gunin_igor75.githubapp.data.UserRemoteMediator
import com.github.gunin_igor75.githubapp.data.local.db.UserDao
import com.github.gunin_igor75.githubapp.data.mappers.toUser
import com.github.gunin_igor75.githubapp.domain.model.User
import com.github.gunin_igor75.githubapp.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(
    private val userDao: UserDao,
    private val userRemoteMediator: UserRemoteMediator,
) : UserRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getUsers(): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                initialLoadSize = PAGE_SIZE
            ),
            remoteMediator = userRemoteMediator,
            pagingSourceFactory = { userDao.pagingSource() }
        )
            .flow
            .map { pagingData ->
                pagingData.map { it.toUser() }
            }
    }

    private companion object {
        const val PAGE_SIZE = 30
    }
}