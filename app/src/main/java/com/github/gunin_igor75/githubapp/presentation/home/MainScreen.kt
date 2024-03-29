package com.github.gunin_igor75.githubapp.presentation.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.gunin_igor75.githubapp.navigation.AppNavGraph
import com.github.gunin_igor75.githubapp.navigation.rememberNavigationState
import com.github.gunin_igor75.githubapp.presentation.details.UserDetailsScreenContent
import com.github.gunin_igor75.githubapp.presentation.users.UsersScreenContent


@Composable
fun MainScreen() {
    val navigationState = rememberNavigationState()
    val navHostController = navigationState.navHostController
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        AppNavGraph(
            navHostController = navHostController,
            usersScreenContent = {
                UsersScreenContent(
                    onClickItem = { name ->
                        navigationState.navigateToUserDetails(name)
                    }
                )
            },
            userDetailsScreenContent = { name ->
                UserDetailsScreenContent(
                    name = name,
                    noClickBack = {
                        navHostController.popBackStack()
                    }
                )
            }
        )
    }
}