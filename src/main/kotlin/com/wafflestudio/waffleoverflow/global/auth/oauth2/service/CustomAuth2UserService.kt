package com.wafflestudio.waffleoverflow.global.auth.oauth2.service

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.DefaultOAuth2User
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import java.util.Collections

@Service
class CustomAuth2UserService : DefaultOAuth2UserService() {
    override fun loadUser(userRequest: OAuth2UserRequest?): OAuth2User {
        val oauth2User: OAuth2User = super.loadUser(userRequest)

        return when (userRequest?.clientRegistration?.registrationId) {
            "google" -> getGoogleUser(oauth2User, userRequest)
            else -> oauth2User
        }
    }

    fun getGoogleUser(oAuth2User: OAuth2User, userRequest: OAuth2UserRequest?): OAuth2User {
        val userAttributes: HashMap<String, Any?> = hashMapOf(
            "email" to oAuth2User.attributes["email"],
            "username" to oAuth2User.attributes["name"],
        )

        return DefaultOAuth2User(
            Collections.singleton(SimpleGrantedAuthority("ROLE_USER")),
            userAttributes, "email"
        )
    }
}
