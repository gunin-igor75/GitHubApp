package com.github.gunin_igor75.githubapp.di

import androidx.lifecycle.ViewModel
import com.github.gunin_igor75.githubapp.presentation.details.UserDetailsViewModel
import com.github.gunin_igor75.githubapp.presentation.users.UsersViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(UsersViewModel::class)
    @Binds
    fun bindsUsersViewModel(viewModel: UsersViewModel): ViewModel

    @IntoMap
    @ViewModelKey(UserDetailsViewModel::class)
    @Binds
    fun bindsUserDetailsViewModel(viewModel: UserDetailsViewModel): ViewModel
}