package com.wafflestudio.waffleoverflow.domain.user.service

import com.wafflestudio.waffleoverflow.domain.user.dto.UserDto
import com.wafflestudio.waffleoverflow.domain.user.exception.BadGrantTypeException
import com.wafflestudio.waffleoverflow.domain.user.exception.EmptyRequestException
import com.wafflestudio.waffleoverflow.domain.user.exception.InvalidEmailFormatException
import com.wafflestudio.waffleoverflow.domain.user.exception.InvalidPasswordFormatException
import com.wafflestudio.waffleoverflow.domain.user.exception.TooLongUsernameException
import com.wafflestudio.waffleoverflow.domain.user.exception.UserNotFoundException
import com.wafflestudio.waffleoverflow.domain.user.exception.UserAlreadyExistsException
import com.wafflestudio.waffleoverflow.domain.user.exception.UserSignUpBadRequestException
import com.wafflestudio.waffleoverflow.domain.user.model.User
import com.wafflestudio.waffleoverflow.domain.user.repository.UserRepository
import com.wafflestudio.waffleoverflow.domain.user.util.S3Utils
import com.wafflestudio.waffleoverflow.global.auth.jwt.JwtTokenProvider
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.util.regex.Pattern

@Service
@Transactional
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtTokenProvider: JwtTokenProvider,
    private val s3Utils: S3Utils
) {
    fun signup(signupRequest: UserDto.SignupRequest): User {
        if (!isEmailValid(signupRequest.email)) throw InvalidEmailFormatException()
        if (!isPasswordValid(signupRequest.password)) throw InvalidPasswordFormatException()
        if (!checkUsernameLength(signupRequest.username)) throw TooLongUsernameException()
        if (userRepository.existsUserByUsername(signupRequest.username) ||
            userRepository.existsUserByEmail(signupRequest.email)
        ) throw throw UserAlreadyExistsException()
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

    fun deleteMyAccount(user: User) {
        userRepository.delete(user)
    }

    fun findUserById(id: Long): User {
        return userRepository.findByIdOrNull(id) ?: throw UserNotFoundException("User $id does not exist")
    }

    fun loadUserInfo(user: User): User {
        return userRepository.findByEmail(user.email) ?: throw UserNotFoundException()
    }

    private fun checkUsernameLength(username: String): Boolean {
        return username.length <= 20
    }

    private fun isEmailValid(email: String): Boolean {
        return Pattern.compile(
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@" +
                "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?" +
                "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\." +
                "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?" +
                "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|" +
                "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        ).matcher(email).matches()
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.matches(
            "^(?=.*[a-zA-Z0-9])(?=.*[a-zA-Z!@#\$%^&*])(?=.*[0-9!@#\$%^&*]).{6,15}\$"
                .toRegex()
        )
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

    fun editUserProfile(
        user: User,
        editProfileRequest: UserDto.EditProfileRequest,
    ): User {
        val location = editProfileRequest.location
        val userTitle = editProfileRequest.userTitle
        val aboutMe = editProfileRequest.aboutMe
        val websiteLink = editProfileRequest.websiteLink
        val githubLink = editProfileRequest.githubLink

        if (location == null &&
            userTitle == null &&
            aboutMe == null &&
            websiteLink == null &&
            githubLink == null
        ) throw EmptyRequestException("Empty request.")

        if (location != null) user.location = location
        if (userTitle != null) user.userTitle = userTitle
        if (aboutMe != null) user.aboutMe = aboutMe
        if (websiteLink != null) user.websiteLink = websiteLink
        if (githubLink != null) user.githubLink = githubLink

        return userRepository.save(user)
    }
}
