package com.wafflestudio.waffleoverflow.global.auth.model

import com.wafflestudio.waffleoverflow.domain.user.model.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserPrincipal(val user: User) : UserDetails {
    override fun getUsername(): String {
        return user.email
    }

    override fun getPassword(): String? {
        return user.password
    }

    override fun getAuthorities(): Collection<GrantedAuthority> {
        val authority: List<String> = user.authorites.split(",").filter { it.isNotEmpty() }
        return authority.map { author: String? -> SimpleGrantedAuthority(author) }
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}
