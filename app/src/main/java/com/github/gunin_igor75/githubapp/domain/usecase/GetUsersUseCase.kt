package com.github.gunin_igor75.githubapp.domain.usecase

import com.github.gunin_igor75.githubapp.domain.repository.UserRepository
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke() = repository.getUsers()
}