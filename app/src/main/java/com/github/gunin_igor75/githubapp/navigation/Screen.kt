package com.github.gunin_igor75.githubapp.navigation

sealed class Screen(
    val route: String
) {
    data object Users: Screen(ROUTE_USERS)
    data object UserDetails: Screen(ROUTE_USER_DETAILS)




    private companion object{
        const val ROUTE_USERS = "users"
        const val ROUTE_USER_DETAILS = "user_details"
    }
}