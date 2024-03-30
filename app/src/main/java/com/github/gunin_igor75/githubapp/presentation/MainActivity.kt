package com.github.gunin_igor75.githubapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.github.gunin_igor75.githubapp.presentation.home.MainScreen
import com.github.gunin_igor75.githubapp.presentation.ui.theme.GitHubAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GitHubAppTheme {
                MainScreen()
            }
        }
    }
}

