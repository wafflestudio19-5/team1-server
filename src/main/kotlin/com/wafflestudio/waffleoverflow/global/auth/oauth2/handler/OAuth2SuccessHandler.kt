package com.wafflestudio.waffleoverflow.global.auth.oauth2.handler

import com.wafflestudio.waffleoverflow.domain.user.model.User
import com.wafflestudio.waffleoverflow.domain.user.repository.UserRepository
import com.wafflestudio.waffleoverflow.global.auth.jwt.JwtTokenProvider
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class OAuth2SuccessHandler(
    private val userRepository: UserRepository,
    private val jwtTokenProvider: JwtTokenProvider,
) : AuthenticationSuccessHandler {
    override fun onAuthenticationSuccess(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authentication: Authentication?
    ) {
        val oAuth2User = authentication?.principal as OAuth2User
        val email = oAuth2User.attributes["email"] as String
        val username = oAuth2User.attributes["username"] as String
        val accessToken = jwtTokenProvider.generateToken(email)

        if (request == null || response == null)
            throw ServletException()

        val existUser = userRepository.findByEmail(email)

        val user = existUser ?: run {
            userRepository.save(
                User(
                    email = email,
                    username = username,
                    accessToken = accessToken
                )
            )
        }

        response.contentType = "application/json"
        response.characterEncoding = "utf-8"
        // response.addHeader("Authentication", accessToken)
        response.addHeader("Location", "https://www.waffleoverflow.shop/social?token=$accessToken")
        response.writer.write("{\"accessToken\" : " + "\"" + accessToken + "\"" + "}")
        response.status = HttpServletResponse.SC_TEMPORARY_REDIRECT
    }
}
