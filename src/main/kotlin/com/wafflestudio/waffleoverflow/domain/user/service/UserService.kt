package com.wafflestudio.waffleoverflow.domain.user.service

import com.wafflestudio.waffleoverflow.domain.user.dto.UserDto
import com.wafflestudio.waffleoverflow.domain.user.exception.BadGrantTypeException
import com.wafflestudio.waffleoverflow.domain.user.exception.UserAlreadyExistsException
import com.wafflestudio.waffleoverflow.domain.user.exception.UserSignUpBadRequestException
import com.wafflestudio.waffleoverflow.domain.user.model.User
import com.wafflestudio.waffleoverflow.domain.user.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
) {
    fun signup(signupRequest: UserDto.SignupRequest): User {
        if (userRepository.existsUserByUsername(signupRequest.username)) throw UserAlreadyExistsException()
        val user: User?
        val email = signupRequest.email
        val username = signupRequest.username
        val encodedPassword = passwordEncoder.encode(signupRequest.password)
        val grantType = signupRequest.grantType

        when (grantType) {
            // Oauth 영역 추가 예정
            "PASSWORD" -> {
                if (email == null || signupRequest.password == null) {
                    throw UserSignUpBadRequestException()
                }
                user = User(username, email, encodedPassword)
            }
            else -> {
                throw BadGrantTypeException()
            }
        }
        return userRepository.save(user)
    }
}
