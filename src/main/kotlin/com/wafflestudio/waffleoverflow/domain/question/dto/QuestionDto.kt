package com.wafflestudio.waffleoverflow.domain.question.dto

import com.wafflestudio.waffleoverflow.domain.answer.dto.AnswerDto
import com.wafflestudio.waffleoverflow.domain.comment.dto.CommentDto
import com.wafflestudio.waffleoverflow.domain.question.model.Question
import com.wafflestudio.waffleoverflow.domain.tag.dto.TagDto
import com.wafflestudio.waffleoverflow.domain.user.dto.UserDto
import com.wafflestudio.waffleoverflow.domain.vote.model.VoteStatus

class QuestionDto {
    data class Response(
        val id: Long,
        val user: UserDto.Response,
        val title: String,
        val body: String,
        val vote: Int,
        val comments: List<CommentDto.Response>,
        val tags: List<TagDto.Response>,
        val answers: List<AnswerDto.Response>
    ) {
        constructor(question: Question) : this(
            question.id,
            UserDto.Response(question.user),
            question.title,
            question.body,
            question.votes.count { it.status == VoteStatus.UP } - question.votes.count { it.status == VoteStatus.DOWN },
            question.comments.map { CommentDto.Response(it) },
            question.questionTags.map { TagDto.Response(it.tag) },
            question.answers.map { AnswerDto.Response(it) }
        )
    }
}
