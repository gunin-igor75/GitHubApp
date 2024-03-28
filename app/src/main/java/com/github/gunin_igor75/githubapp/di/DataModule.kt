package com.github.gunin_igor75.githubapp.di

import android.content.Context
import com.github.gunin_igor75.githubapp.data.local.db.UserDao
import com.github.gunin_igor75.githubapp.data.local.db.UserDataBase
import com.github.gunin_igor75.githubapp.data.network.api.ApiFactory
import com.github.gunin_igor75.githubapp.data.network.api.ApiService
import com.github.gunin_igor75.githubapp.data.reposotory.UserRepositoryImp
import com.github.gunin_igor75.githubapp.domain.UserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    fun bindsUserRepository(impl: UserRepositoryImp): UserRepository

    companion object {

        @Provides
        fun providesUserDataBase(context: Context): UserDataBase {
            return UserDataBase.getInstance(context)
        }

        @Provides
        fun providesUserDao(userDataBase: UserDataBase): UserDao {
            return userDataBase.userDao()
        }

        @Provides
        fun providesApiService(): ApiService = ApiFactory.apiService
    }
}