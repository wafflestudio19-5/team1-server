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
        val upVote: Int,
        val downVote: Int,
        val comments: List<CommentDto.Wrapper>,
        val tags: List<TagDto.Wrapper>,
        val answers: List<AnswerDto.Response>
    ) {
        constructor(question: Question) : this(
            question.id,
            UserDto.Response(question.user),
            question.title,
            question.bodyPath,
            question.votes.count { it.status == VoteStatus.UP },
            question.votes.count { it.status == VoteStatus.DOWN },
            question.comments.map { CommentDto.Wrapper(it) },
            question.questionTags.map { TagDto.Wrapper(it.tag) },
            question.answers.map { AnswerDto.Response(it) }
        )
    }
}
