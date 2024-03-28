package com.github.gunin_igor75.githubapp.data.mappers

import com.github.gunin_igor75.githubapp.data.local.model.UserEntity
import com.github.gunin_igor75.githubapp.data.network.dto.UserDetailsDto
import com.github.gunin_igor75.githubapp.data.network.dto.UserDto
import com.github.gunin_igor75.githubapp.domain.model.User
import com.github.gunin_igor75.githubapp.domain.model.UserDetails
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter


fun UserDto.toUserEntity(): UserEntity {
    return UserEntity(
        id = id,
        title = title,
        avatar = avatar
    )
}

fun UserEntity.toUser(): User {
    return User(
        id = id,
        title = title,
        avatar = avatar
    )
}

fun UserDetailsDto.toUserDetails(): UserDetails {
    return UserDetails(
        name = name,
        avatar = avatar,
        email = email,
        organization = organization,
        following = following,
        followers = followers,
        createdAt = createdAt.convertString()
    )
}

fun String.convertString(): String {
    val zoneFormatter = DateTimeFormatter.ISO_INSTANT.withZone(ZoneId.systemDefault())
    val zoneDate = ZonedDateTime.parse(this, zoneFormatter)
    val pattern = "dd MMMM yyyy"
    val formatter = DateTimeFormatter.ofPattern(pattern)
    return zoneDate.format(formatter)
}

