package com.wafflestudio.waffleoverflow.domain.question.dto

import com.wafflestudio.waffleoverflow.domain.answer.dto.AnswerDto
import com.wafflestudio.waffleoverflow.domain.comment.dto.CommentDto
import com.wafflestudio.waffleoverflow.domain.question.model.Question
import com.wafflestudio.waffleoverflow.domain.tag.dto.TagDto
import com.wafflestudio.waffleoverflow.domain.user.dto.UserDto
import com.wafflestudio.waffleoverflow.domain.vote.model.VoteStatus
import java.time.LocalDateTime
import javax.validation.constraints.NotBlank

class QuestionDto {
    data class Response(
        val id: Long,
        val user: UserDto.SimpleResponse,
        val title: String,
        val body: String,
        val vote: Int,
        val comments: List<CommentDto.Response>,
        val tags: List<TagDto.Response>,
        val answers: List<AnswerDto.Response>,
        val createdAt: LocalDateTime,
        val editedAt: LocalDateTime?,
    ) {
        constructor(question: Question) : this(
            question.id,
            UserDto.SimpleResponse(question.user),
            question.title,
            question.body,
            question.votes.count { it.status == VoteStatus.UP } - question.votes.count { it.status == VoteStatus.DOWN },
            question.comments.map { CommentDto.Response(it) },
            question.questionTags.map { TagDto.Response(it.tag) },
            question.answers.map { AnswerDto.Response(it) },
            question.createdAt!!,
            question.editedAt,
        )
    }

    data class Request(
        @NotBlank
        val title: String,
        @NotBlank
        val body: String,
    )

    data class ResponseSummary(
        val id: Long,
        val title: String,
        val createdAt: LocalDateTime?,
    ) {
        constructor(question: Question) : this(
            id = question.id,
            title = question.title,
            createdAt = question.createdAt,
        )
    }
}
