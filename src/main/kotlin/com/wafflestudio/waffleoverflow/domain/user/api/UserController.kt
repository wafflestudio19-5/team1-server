package com.wafflestudio.waffleoverflow.domain.user.api

import com.wafflestudio.waffleoverflow.domain.user.dto.UserDto
import com.wafflestudio.waffleoverflow.domain.user.model.User
import com.wafflestudio.waffleoverflow.domain.user.service.UserService
import com.wafflestudio.waffleoverflow.global.auth.CurrentUser
import org.springframework.http.HttpStatus
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

@ResponseStatus
@RestController
@RequestMapping("/api/user")
class UserController(
    private val userService: UserService,
) {
    @PostMapping("/signup/")
    @ResponseStatus(HttpStatus.OK)
    fun signup(
        @Valid @RequestBody signupRequest: UserDto.SignupRequest,
        response: HttpServletResponse
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
        if (auth != null) SecurityContextLogoutHandler().logout(request, response, auth)
    }

    @GetMapping("/me/")
    @ResponseStatus(HttpStatus.OK)
    fun loadUser(
        @CurrentUser user: User,
    ): UserDto.Response {
        val thisUser = userService.loadUserInfo(user)
        return UserDto.Response(thisUser)
    }
}
