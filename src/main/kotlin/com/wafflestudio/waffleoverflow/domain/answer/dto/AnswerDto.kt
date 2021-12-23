package com.wafflestudio.waffleoverflow.domain.answer.dto

import com.wafflestudio.waffleoverflow.domain.comment.dto.CommentDto
import com.wafflestudio.waffleoverflow.domain.user.dto.UserDto
import javax.validation.constraints.NotEmpty

class AnswerDto {
    data class Request(
        @NotEmpty
        val body: String
    )

    data class Response(
        val id: Long,
        val user: UserDto.Response,
        val title: String,
        val body: String,
        val votes: Int,
        val comments: List<CommentDto.Response>,
    )
}
