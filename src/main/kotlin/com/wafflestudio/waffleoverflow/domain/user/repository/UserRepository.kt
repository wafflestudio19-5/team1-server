package com.wafflestudio.waffleoverflow.domain.user.repository

import com.wafflestudio.waffleoverflow.domain.user.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long?> {
    fun existsByEmail(email: String): Boolean
}
