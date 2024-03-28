package com.github.gunin_igor75.githubapp.presentation.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.github.gunin_igor75.githubapp.domain.usecase.GetUsersUseCase
import javax.inject.Inject

class UsersViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
): ViewModel() {

    val usersPagingFlow = getUsersUseCase()
        .cachedIn(viewModelScope)
}