package com.wafflestudio.waffleoverflow.global.auth

import org.springframework.security.core.annotation.AuthenticationPrincipal

@Target
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@AuthenticationPrincipal(expression = "user")
annotation class CurrentUser(val require: Boolean = true)
