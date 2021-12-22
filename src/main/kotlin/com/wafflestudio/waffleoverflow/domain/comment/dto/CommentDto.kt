package com.wafflestudio.waffleoverflow.domain.comment.dto

import com.wafflestudio.waffleoverflow.domain.comment.model.Comment
import com.wafflestudio.waffleoverflow.domain.user.dto.UserDto

class CommentDto {
    data class Response(
        val id: Long,
        val user: UserDto.Response,
        val body: String,
        val questionId: Long?,
        val answerId: Long?,
    ) {
        constructor(comment: Comment) : this(
            comment.id,
            UserDto.Response(comment.user),
            comment.body,
            comment.question?.id,
            comment.answer?.id
        )
    }
}
