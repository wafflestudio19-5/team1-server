package com.wafflestudio.waffleoverflow.domain.comment.service

import com.wafflestudio.waffleoverflow.domain.answer.model.Answer
import com.wafflestudio.waffleoverflow.domain.comment.dto.CommentDto
import com.wafflestudio.waffleoverflow.domain.comment.exception.CommentNotFoundException
import com.wafflestudio.waffleoverflow.domain.comment.exception.NotCommentAuthorException
import com.wafflestudio.waffleoverflow.domain.comment.exception.TooLongCommentException
import com.wafflestudio.waffleoverflow.domain.comment.model.Comment
import com.wafflestudio.waffleoverflow.domain.comment.repository.CommentRepository
import com.wafflestudio.waffleoverflow.domain.question.model.Question
import com.wafflestudio.waffleoverflow.domain.user.model.User
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CommentService(
    private val commentRepository: CommentRepository
) {
    fun findById(id: Long): Comment {
        return commentRepository.findByIdOrNull(id) ?: throw CommentNotFoundException("Comment $id does not exist")
    }

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

    fun editComment(
        requestBody: CommentDto.Request,
        comment: Comment,
        user: User,
    ): Comment {
        validateUser(user, comment)

        val body = requestBody.body
        checkCommentLength(body)

        comment.body = body
        commentRepository.save(comment)

        return comment
    }

    fun deleteComment(
        comment: Comment,
        user: User
    ) {
        validateUser(user, comment)
        commentRepository.delete(comment)
    }

    private fun addComment(
        requestBody: CommentDto.Request,
        user: User,
        question: Question?,
        answer: Answer?,
    ): Comment {
        val body = requestBody.body
        checkCommentLength(body)
        val comment = Comment(user, body, question, answer)
        commentRepository.save(comment)
        return comment
    }

    private fun validateUser(
        user: User,
        comment: Comment
    ) {
        if (user.id != comment.user.id) {
            throw NotCommentAuthorException("User $user.id is not the author of comment $comment.id")
        }
    }

    private fun checkCommentLength(
        body: String
    ) {
        if (body.length >= 250) {
            throw TooLongCommentException("Comment is longer than 250 characters")
        }
    }
}
