package com.wafflestudio.waffleoverflow.domain.user.api

import com.wafflestudio.waffleoverflow.domain.user.dto.UserDto
import com.wafflestudio.waffleoverflow.domain.user.model.User
import com.wafflestudio.waffleoverflow.domain.user.service.UserService
import com.wafflestudio.waffleoverflow.global.auth.CurrentUser
import com.wafflestudio.waffleoverflow.global.auth.jwt.JwtTokenProvider
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

@ResponseStatus
@RestController
@RequestMapping("/api/user")
class UserController(
    private val userService: UserService,
    private val jwtTokenProvider: JwtTokenProvider,
) {
    @PostMapping("/signup/")
    @ResponseStatus(HttpStatus.OK)
    fun signup(
        @Valid @RequestBody signupRequest: UserDto.SignupRequest,
        response: HttpServletResponse
    ): UserDto.Response {
        val user = userService.signup(signupRequest)
        response.addHeader("Authentication", jwtTokenProvider.generateToken(user.email))
        return UserDto.Response(user)
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
