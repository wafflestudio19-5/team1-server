package com.wafflestudio.waffleoverflow.domain.comment.dto

import com.wafflestudio.waffleoverflow.domain.comment.model.Comment
import com.wafflestudio.waffleoverflow.domain.user.dto.UserDto
import java.time.LocalDateTime
import javax.validation.constraints.NotBlank

class CommentDto {
    data class Response(
        val id: Long,
        val user: UserDto.ResponseSummary,
        val body: String,
        val questionId: Long?,
        val answerId: Long?,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime
    ) {
        constructor(comment: Comment) : this(
            comment.id,
            UserDto.ResponseSummary(comment.user),
            comment.body,
            comment.question?.id,
            comment.answer?.id,
            comment.createdAt!!,
            comment.updatedAt!!
        )
    }

    data class Request(
        @NotBlank
        val body: String
    )
}
