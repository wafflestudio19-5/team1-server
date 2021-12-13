package com.wafflestudio.waffleoverflow.domain.comment.repository

import com.wafflestudio.waffleoverflow.domain.comment.model.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long?>
