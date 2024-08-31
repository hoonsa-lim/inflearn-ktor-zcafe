package com.example.service

import com.example.config.AuthenticatedUser
import com.example.domain.repository.CafeUserRepository
import com.example.shared.dto.UserDto
import io.ktor.server.sessions.*

class LoginService(
    private val userService: UserService,
    private val cafeUserRepository: CafeUserRepository,
){
    suspend fun login(cafeUserLoginRequest: UserDto.LoginRequest, sessions: CurrentSession) {
        checkNoSession(sessions)

        val user = userService.getCafeUser(
            nickname = cafeUserLoginRequest.nickname,
            plainPassword = cafeUserLoginRequest.plainPassword,
        )

        sessions.set(AuthenticatedUser(user.id!!, user.roles))
    }

    suspend fun signup(request: UserDto.LoginRequest, sessions: CurrentSession) {
        checkNoSession(sessions)

        val user = userService.createCustomer(
            nickname = request.nickname,
            plainPassword = request.plainPassword,
        )

        sessions.set(AuthenticatedUser(user.id!!, user.roles))
    }

    suspend fun logout(sessions: CurrentSession) {
        sessions.clear<AuthenticatedUser>()
    }

    private fun checkNoSession(sessions: CurrentSession) {
        val authenticatedUser = sessions.get<AuthenticatedUser>()

        if (authenticatedUser != null) throw RuntimeException()
    }
}