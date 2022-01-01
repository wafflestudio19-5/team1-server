package com.wafflestudio.waffleoverflow.domain.user.dto

import com.wafflestudio.waffleoverflow.domain.user.model.User
import javax.validation.constraints.NotBlank

class UserDto {
    data class Response(
        val id: Long,
        val email: String,
        val username: String,
    ) {
        constructor(user: User) : this(
            id = user.id,
            email = user.email,
            username = user.username,
        )
    }

    data class SignupRequest(
        @field:NotBlank
        val email: String,

        @field:NotBlank
        val username: String,

        @field:NotBlank
        val password: String,

        @field:NotBlank
        var grantType: String? = "PASSWORD",
    )
}
