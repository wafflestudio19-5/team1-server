package com.wafflestudio.waffleoverflow.domain.answer.dto

import com.wafflestudio.waffleoverflow.domain.answer.model.Answer
import com.wafflestudio.waffleoverflow.domain.comment.dto.CommentDto
import com.wafflestudio.waffleoverflow.domain.user.dto.UserDto
import com.wafflestudio.waffleoverflow.domain.vote.model.VoteStatus
import javax.validation.constraints.NotEmpty

class AnswerDto {
    data class Request(
        @NotEmpty
        val body: String
    )

    data class Response(
        val id: Long,
        val user: UserDto.Response,
        val body: String,
        val votes: Int,
        val comments: List<CommentDto.Response>,
        val accepted: Boolean
    ) {
        constructor(answer: Answer) : this(
            answer.id,
            UserDto.Response(answer.user),
            answer.body,
            answer.votes.count { it.status == VoteStatus.UP } - answer.votes.count { it.status == VoteStatus.DOWN },
            answer.comments.map { CommentDto.Response(it) },
            answer.accepted
        )
    }
}
