package com.wafflestudio.stackoverflow.domain.user.service

import com.wafflestudio.stackoverflow.domain.user.dto.UserDto
import com.wafflestudio.stackoverflow.domain.user.exception.UserAlreadyExistsException
import com.wafflestudio.stackoverflow.domain.user.model.User
import com.wafflestudio.stackoverflow.domain.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    fun signup(signupRequest: UserDto.SignupRequest): User {
        if (userRepository.existsByEmail(signupRequest.email)) throw UserAlreadyExistsException()
        val email = signupRequest.email
        val name = signupRequest.name
        val password = signupRequest.password

        return userRepository.save(User(email, name, password))
    }
}