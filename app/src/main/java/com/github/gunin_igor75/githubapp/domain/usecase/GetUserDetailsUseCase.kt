package com.github.gunin_igor75.githubapp.domain.usecase

import com.github.gunin_igor75.githubapp.domain.repository.UserDetailsRepository
import javax.inject.Inject

class GetUserDetailsUseCase @Inject constructor(
    private val repository: UserDetailsRepository
) {
    suspend operator fun invoke(name: String) = repository.getUser(name)
}