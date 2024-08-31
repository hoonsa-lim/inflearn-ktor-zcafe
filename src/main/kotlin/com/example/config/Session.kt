package com.example.config

import com.example.shared.CafeUserRole
import io.ktor.server.application.*
import io.ktor.server.sessions.*
import kotlinx.serialization.Serializable

fun Application.configureSession(){
    install(Sessions){
        cookie<AuthenticatedUser>(name = "CU_SESSION_ID", SessionStorageMemory()){
            cookie.path = "/"
        }
    }
}

@Serializable
data class AuthenticatedUser(
    val userId: Long,
    val userRole: List<CafeUserRole>
){
    companion object{
        fun none() : AuthenticatedUser = AuthenticatedUser(0, listOf())
    }
}