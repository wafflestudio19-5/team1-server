package com.wafflestudio.waffleoverflow.domain.user.service

import com.wafflestudio.waffleoverflow.domain.user.dto.UserDto
import com.wafflestudio.waffleoverflow.domain.user.exception.BadGrantTypeException
import com.wafflestudio.waffleoverflow.domain.user.exception.CouldNotFoundUser
import com.wafflestudio.waffleoverflow.domain.user.exception.UserAlreadyExistsException
import com.wafflestudio.waffleoverflow.domain.user.exception.UserSignUpBadRequestException
import com.wafflestudio.waffleoverflow.domain.user.model.User
import com.wafflestudio.waffleoverflow.domain.user.repository.UserRepository
import com.wafflestudio.waffleoverflow.domain.user.util.S3Utils
import com.wafflestudio.waffleoverflow.global.auth.jwt.JwtTokenProvider
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtTokenProvider: JwtTokenProvider,
    private val s3Utils: S3Utils
) {
    fun signup(signupRequest: UserDto.SignupRequest): User {
        if (userRepository.existsUserByUsername(signupRequest.username)) throw UserAlreadyExistsException()
        if (userRepository.existsUserByEmail(signupRequest.email)) throw UserAlreadyExistsException()
        val user: User?
        val email = signupRequest.email
        val username = signupRequest.username
        val encodedPassword = passwordEncoder.encode(signupRequest.password)
        val grantType = signupRequest.grantType
        val accessToken = jwtTokenProvider.generateToken(email)

        when (grantType) {
            // Oauth 영역 추가 예정
            "PASSWORD" -> {
                if (email == null || signupRequest.password == null || signupRequest.password == "") {
                    throw UserSignUpBadRequestException()
                }
                user = User(email, username, encodedPassword, accessToken = accessToken)
            }
            else -> {
                throw BadGrantTypeException()
            }
        }
        return userRepository.save(user)
    }

    fun loadUserInfo(user: User): User {
        return userRepository.findByEmail(user.email) ?: throw CouldNotFoundUser()
    }

    fun editProfileImage(
        user: User,
        multipartFile: MultipartFile
    ): User {
        val urlPath = s3Utils.upload(multipartFile)

        user.s3Path = urlPath
        userRepository.save(user)

        return user
    }
}
