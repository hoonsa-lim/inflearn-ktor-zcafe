package com.example.shared.dto

import kotlinx.serialization.Serializable

class UserDto {
    @Serializable
    data class LoginRequest(
        val nickname: String,
        val plainPassword: String,              //실제 데이터는 헤싱된 값이 들어갈 것임, 그래서 plain 붙임
    )
}