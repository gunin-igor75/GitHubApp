package com.github.gunin_igor75.githubapp

import android.app.Application
import com.github.gunin_igor75.githubapp.di.ApplicationComponent
import com.github.gunin_igor75.githubapp.di.DaggerApplicationComponent

class GitHubApp: Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerApplicationComponent.factory().create(this)
    }
}