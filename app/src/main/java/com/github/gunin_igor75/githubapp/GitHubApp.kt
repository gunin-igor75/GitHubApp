package com.github.gunin_igor75.githubapp

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.github.gunin_igor75.githubapp.di.ApplicationComponent
import com.github.gunin_igor75.githubapp.di.DaggerApplicationComponent

class GitHubApp : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}

@Composable
fun getComponentApp(): ApplicationComponent {
    return (LocalContext.current.applicationContext as GitHubApp).component
}