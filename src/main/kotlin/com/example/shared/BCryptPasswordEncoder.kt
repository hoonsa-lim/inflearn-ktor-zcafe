package com.example.shared

import org.mindrot.jbcrypt.BCrypt

class BCryptPasswordEncoder {   //일반적으로 비밀번호 해싱하는데 많이 사용하는 방식
    fun encode(password: String): String {
        return BCrypt.hashpw(password, BCrypt.gensalt())
    }

    fun matches(password: String, hashed: String): Boolean {
        return BCrypt.checkpw(password, hashed)
    }
}
