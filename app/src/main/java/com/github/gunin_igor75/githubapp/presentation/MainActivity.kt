package com.github.gunin_igor75.githubapp.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.github.gunin_igor75.githubapp.data.network.api.ApiFactory
import com.github.gunin_igor75.githubapp.presentation.ui.theme.GitHubAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val scope = CoroutineScope(Dispatchers.IO)
//
//        val apiService = ApiFactory.apiService
//
//        scope.launch {
//            val users = apiService.loadUsers(
//                offset = 5,
//                limit = 10
//            )
//            val user = apiService.loadUser("gunin-igor75")
//            Log.d("MainActivity", user.toString())
//        }

        setContent {
            GitHubAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
            }
        }
    }
}

