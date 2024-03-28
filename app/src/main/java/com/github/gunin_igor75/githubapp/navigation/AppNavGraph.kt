package com.github.gunin_igor75.githubapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    usersScreenContent: @Composable () -> Unit,
    userDetailsScreenContent: @Composable (String) -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Users.route,
    ){
        composable(Screen.Users.route){
            usersScreenContent()
        }

    }
}