package com.github.gunin_igor75.githubapp.presentation.details

import androidx.lifecycle.ViewModel
import com.github.gunin_igor75.githubapp.domain.model.UserDetailsState
import com.github.gunin_igor75.githubapp.domain.usecase.GetUserDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UserDetailsViewModel @Inject constructor(
    private val getUserDetailsUseCase: GetUserDetailsUseCase,
) : ViewModel() {

    private val _userDetails: MutableStateFlow<UserDetailsState> = MutableStateFlow(
        UserDetailsState.Initial
    )
    val userDetails: StateFlow<UserDetailsState> = _userDetails.asStateFlow()

    fun getUserDetails(name: String) {
        viewModelScope.launch {
            _userDetails.emit(UserDetailsState.Loading)
            _userDetails.emit(getUserDetailsUseCase(name))
        }
    }
}