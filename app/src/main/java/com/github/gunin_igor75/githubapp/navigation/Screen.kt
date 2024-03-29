package com.github.gunin_igor75.githubapp.navigation

sealed class Screen(
    val route: String
) {
    data object Users: Screen(ROUTE_USERS)
    data object UserDetails: Screen(ROUTE_USER_DETAILS){

        private const val ROUTE_FOR_ARG = "user_details"
        fun getRouteWithArgs(name: String): String {
            return "$ROUTE_FOR_ARG/$name"
        }
    }

    companion object{
        const val KEY_NAME = "name"
        private const val ROUTE_USERS = "users"
        private const val ROUTE_USER_DETAILS = "user_details/{$KEY_NAME}"

    }
}