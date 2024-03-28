package com.github.gunin_igor75.githubapp.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun getViewModelFactory(): ViewModelFactory

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
        ): ApplicationComponent
    }
}