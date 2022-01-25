package com.wafflestudio.waffleoverflow.domain.answer.dto

import com.wafflestudio.waffleoverflow.domain.answer.model.Answer
import com.wafflestudio.waffleoverflow.domain.comment.dto.CommentDto
import com.wafflestudio.waffleoverflow.domain.user.dto.UserDto
import com.wafflestudio.waffleoverflow.domain.vote.model.VoteStatus
import java.time.LocalDateTime
import javax.validation.constraints.NotEmpty

class AnswerDto {
    data class Request(
        @NotEmpty
        val body: String
    )

    data class Response(
        val id: Long,
        val user: UserDto.SimpleResponse,
        val body: String,
        val votes: Int,
        val comments: List<CommentDto.Response>,
        val accepted: Boolean,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime
    ) {
        constructor(answer: Answer) : this(
            answer.id,
            UserDto.SimpleResponse(answer.user),
            answer.body,
            answer.votes.count { it.status == VoteStatus.UP } - answer.votes.count { it.status == VoteStatus.DOWN },
            answer.comments.map { CommentDto.Response(it) },
            answer.accepted,
            answer.createdAt!!,
            answer.updatedAt!!
        )
    }

    data class ResponseSummary(
        val id: Long,
        val questionTitle: String,
        val questionId: Long,
        val createdAt: LocalDateTime?
    ) {
        constructor(answer: Answer) : this(
            id = answer.id,
            questionTitle = answer.question.title,
            questionId = answer.question.id,
            createdAt = answer.createdAt
        )
    }
}
