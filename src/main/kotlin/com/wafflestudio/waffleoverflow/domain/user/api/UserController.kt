package com.wafflestudio.waffleoverflow.domain.user.api

import com.wafflestudio.waffleoverflow.domain.user.dto.UserDto
import com.wafflestudio.waffleoverflow.domain.user.model.User
import com.wafflestudio.waffleoverflow.domain.user.repository.UserRepository
import com.wafflestudio.waffleoverflow.domain.user.service.UserService
import com.wafflestudio.waffleoverflow.global.auth.CurrentUser
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

@ResponseStatus
@RestController
@RequestMapping("/api/user")
class UserController(
    private val userService: UserService,
    private val userRepository: UserRepository,
) {
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    fun getUsers(
        @PageableDefault(size = 36)
        pageable: Pageable,
    ): Page<UserDto.CardResponse> {
        return userRepository.findAllByDeletedIsFalse(pageable).map { UserDto.CardResponse(it) }
    }

    @PostMapping("/signup/")
    @ResponseStatus(HttpStatus.OK)
    fun signup(
        @Valid @RequestBody signupRequest: UserDto.SignupRequest,
        response: HttpServletResponse,
    ): UserDto.Response {
        val user = userService.signup(signupRequest)
        response.addHeader("Authentication", user.accessToken)
        return UserDto.Response(user)
    }

    @PostMapping("/signout/")
    @ResponseStatus(HttpStatus.OK)
    fun signout(
        request: HttpServletRequest,
        response: HttpServletResponse,
    ) {
        val auth: Authentication = SecurityContextHolder.getContext().authentication
        SecurityContextLogoutHandler().logout(request, response, auth)
    }

    @GetMapping("/{user_id}/")
    @ResponseStatus(HttpStatus.OK)
    fun findUserById(
        @PathVariable user_id: Long,
    ): UserDto.Response {
        return UserDto.Response(
            userService.findUserById(user_id)
        )
    }

    @GetMapping("/me/")
    @ResponseStatus(HttpStatus.OK)
    fun loadUser(
        @CurrentUser user: User,
    ): UserDto.Response {
        return UserDto.Response(
            userService.loadUserInfo(user)
        )
    }

    @PostMapping("/me/image/")
    @ResponseStatus(HttpStatus.OK)
    fun editProfileImage(
        @CurrentUser user: User,
        @RequestParam("image") multipartFile: MultipartFile,
    ): UserDto.Response {
        return UserDto.Response(
            userService.editProfileImage(user, multipartFile)
        )
    }

    @PutMapping("/me/edit/")
    @ResponseStatus(HttpStatus.OK)
    fun editProfile(
        @CurrentUser user: User,
        @Valid @RequestBody editProfileRequest: UserDto.EditProfileRequest,
    ): UserDto.Response {
        return UserDto.Response(
            userService.editUserProfile(user, editProfileRequest)
        )
    }

    @DeleteMapping("/me/remove/")
    @ResponseStatus(HttpStatus.OK)
    fun deleteAccount(
        @CurrentUser user: User,
    ) {
        userService.deleteAccount(user)
    }
}
