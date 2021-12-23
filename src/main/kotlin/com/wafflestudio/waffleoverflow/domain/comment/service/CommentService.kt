package com.wafflestudio.waffleoverflow.domain.comment.service

import com.wafflestudio.waffleoverflow.domain.answer.model.Answer
import com.wafflestudio.waffleoverflow.domain.comment.dto.CommentDto
import com.wafflestudio.waffleoverflow.domain.comment.model.Comment
import com.wafflestudio.waffleoverflow.domain.comment.repository.CommentRepository
import com.wafflestudio.waffleoverflow.domain.question.model.Question
import com.wafflestudio.waffleoverflow.domain.user.model.User
import org.springframework.stereotype.Service

@Service
class CommentService(
    private val commentRepository: CommentRepository
) {
    fun addQuestionComment(
        requestBody: CommentDto.Request,
        user: User,
        question: Question,
    ) = addComment(requestBody, user, question, null)

    fun addAnswerComment(
        requestBody: CommentDto.Request,
        user: User,
        answer: Answer,
    ) = addComment(requestBody, user, null, answer)

    private fun addComment(
        requestBody: CommentDto.Request,
        user: User,
        question: Question?,
        answer: Answer?,
    ): Comment {
        val body = requestBody.body
        val comment = Comment(user, body, question, answer)
        commentRepository.save(comment)
        return comment
    }
}
