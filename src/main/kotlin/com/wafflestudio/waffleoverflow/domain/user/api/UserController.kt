package com.wafflestudio.waffleoverflow.domain.user.api

import com.wafflestudio.waffleoverflow.domain.user.dto.UserDto
import com.wafflestudio.waffleoverflow.domain.user.service.UserService
import com.wafflestudio.waffleoverflow.global.auth.jwt.JwtTokenProvider
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

@RestController
@RequestMapping("/api/user")
class UserController(
    private val userService: UserService,
    private val jwtTokenProvider: JwtTokenProvider,
) {
    @PostMapping("/signup/")
    fun signup(
        @Valid @RequestBody signupRequest: UserDto.SignupRequest,
        response: HttpServletResponse
    ): ResponseEntity<UserDto.Response> {
        val user = userService.signup(signupRequest)
        response.addHeader("Authentication", jwtTokenProvider.generateToken(user.email))
        return ResponseEntity(UserDto.Response(user), HttpStatus.OK)
    }
}
