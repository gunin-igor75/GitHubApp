package com.github.gunin_igor75.githubapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    usersScreenContent: @Composable () -> Unit,
    userDetailsScreenContent: @Composable (String) -> Unit,
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Users.route,
    ) {
        composable(Screen.Users.route) {
            usersScreenContent()
        }

        composable(
            route = Screen.UserDetails.route,
            arguments = listOf(
                navArgument(Screen.KEY_NAME) {
                    type = NavType.StringType
                }
            )
        ) {
            val name = it.arguments?.getString(Screen.KEY_NAME) ?: throw  IllegalArgumentException(
                "This name is null"
            )
            userDetailsScreenContent(name)
        }
    }
}