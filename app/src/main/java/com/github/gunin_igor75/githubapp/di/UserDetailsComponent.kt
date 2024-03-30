package com.github.gunin_igor75.githubapp.di

import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [
        UserDetailsViewModelModule::class
    ]
)
interface UserDetailsComponent {
    fun getViewModelFactory(): ViewModelFactory
    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance name: String,
        ): UserDetailsComponent
    }
}