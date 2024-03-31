package com.github.gunin_igor75.githubapp.di

import androidx.lifecycle.ViewModel
import com.github.gunin_igor75.githubapp.presentation.details.UserDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface UserDetailsViewModelModule {

    @IntoMap
    @ViewModelKey(UserDetailsViewModel::class)
    @Binds
    fun bindsUserDetailsViewModel(viewModel: UserDetailsViewModel): ViewModel
}