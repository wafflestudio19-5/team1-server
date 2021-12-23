package com.wafflestudio.waffleoverflow.domain.question.api

import com.wafflestudio.waffleoverflow.domain.comment.dto.CommentDto
import com.wafflestudio.waffleoverflow.domain.question.dto.QuestionDto
import com.wafflestudio.waffleoverflow.domain.question.service.QuestionService
import com.wafflestudio.waffleoverflow.domain.user.model.User
import com.wafflestudio.waffleoverflow.domain.vote.dto.VoteDto
import com.wafflestudio.waffleoverflow.global.auth.CurrentUser
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/question")
class QuestionController(
    private val questionService: QuestionService
) {
    @GetMapping("/{question_id}")
    @ResponseStatus(HttpStatus.OK)
    fun getQuestion(
        @PathVariable question_id: Long
    ): QuestionDto.Response {
        return QuestionDto.Response(
            questionService.findById(question_id)
        )
    }

    @GetMapping("/{question_id}/comment/")
    @ResponseStatus(HttpStatus.OK)
    fun getComments(
        @PathVariable question_id: Long
    ): List<CommentDto.Response> {
        return questionService.findById(question_id).comments.map { CommentDto.Response(it) }
    }

    @PostMapping("/{question_id}/comment/")
    @ResponseStatus(HttpStatus.CREATED)
    fun addComment(
        @CurrentUser user: User,
        @PathVariable question_id: Long,
        @Valid @RequestBody requestBody: CommentDto.Request
    ): CommentDto.Response {
        val question = questionService.findById(question_id)
        return CommentDto.Response(
            questionService.addComment(requestBody, user, question)
        )
    }

    @PostMapping("/{question_id}/vote/")
    @ResponseStatus(HttpStatus.CREATED)
    fun addVote(
        @CurrentUser user: User,
        @PathVariable question_id: Long,
        @Valid @RequestBody requestBody: VoteDto.Request
    ) {
    }
}
