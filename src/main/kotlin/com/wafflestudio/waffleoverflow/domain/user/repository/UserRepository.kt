package com.wafflestudio.waffleoverflow.domain.user.repository

import com.wafflestudio.waffleoverflow.domain.user.model.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long?> {
    fun findByEmail(email: String): User?
    fun existsUserByUsername(name: String): Boolean
    fun existsUserByEmail(email: String): Boolean
    fun findAllByIsDeletedIsFalse(pageable: Pageable): Page<User>
}
