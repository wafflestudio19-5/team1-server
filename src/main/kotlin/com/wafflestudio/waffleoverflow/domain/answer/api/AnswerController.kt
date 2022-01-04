package com.wafflestudio.waffleoverflow.domain.answer.api

import com.wafflestudio.waffleoverflow.domain.answer.dto.AnswerDto
import com.wafflestudio.waffleoverflow.domain.answer.service.AnswerService
import com.wafflestudio.waffleoverflow.domain.comment.dto.CommentDto
import com.wafflestudio.waffleoverflow.domain.comment.service.CommentService
import com.wafflestudio.waffleoverflow.domain.user.model.User
import com.wafflestudio.waffleoverflow.domain.vote.dto.VoteDto
import com.wafflestudio.waffleoverflow.domain.vote.service.VoteService
import com.wafflestudio.waffleoverflow.global.auth.CurrentUser
import com.wafflestudio.waffleoverflow.global.common.dto.ListResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/answer")
class AnswerController(
    private val answerService: AnswerService,
    private val commentService: CommentService,
    private val voteService: VoteService
) {
    @PutMapping("/{answer_id}/")
    @ResponseStatus(HttpStatus.OK)
    fun editAnswer(
        @CurrentUser user: User,
        @Valid @RequestBody requestBody: AnswerDto.Request,
        @PathVariable answer_id: Long,
    ): AnswerDto.Response {
        val answer = answerService.findById(answer_id)
        return AnswerDto.Response(
            answerService.editAnswer(requestBody, user, answer)
        )
    }

    @DeleteMapping("/{answer_id}/")
    @ResponseStatus(HttpStatus.OK)
    fun deleteAnswer(
        @CurrentUser user: User,
        @PathVariable answer_id: Long
    ): AnswerDto.Response {
        val answer = answerService.findById(answer_id)
        answerService.deleteAnswer(user, answer)
        return AnswerDto.Response(answer)
    }

    @GetMapping("/{answer_id}/comment/")
    @ResponseStatus(HttpStatus.OK)
    fun getComments(
        @PathVariable answer_id: Long
    ): ListResponse<CommentDto.Response> {
        return ListResponse(
            answerService.findById(answer_id).comments.map { CommentDto.Response(it) }
        )
    }

    @PostMapping("/{answer_id}/comment/")
    @ResponseStatus(HttpStatus.CREATED)
    fun addComment(
        @CurrentUser user: User,
        @PathVariable answer_id: Long,
        @Valid @RequestBody requestBody: CommentDto.Request
    ): CommentDto.Response {
        val answer = answerService.findById(answer_id)
        return CommentDto.Response(
            commentService.addAnswerComment(requestBody, user, answer)
        )
    }

    @PutMapping("/comment/{comment_id}/")
    @ResponseStatus(HttpStatus.OK)
    fun editComment(
        @CurrentUser user: User,
        @PathVariable comment_id: Long,
        @Valid @RequestBody requestBody: CommentDto.Request,
    ): CommentDto.Response {
        val comment = commentService.findById(comment_id)
        return CommentDto.Response(
            commentService.editComment(requestBody, comment, user)
        )
    }

    @DeleteMapping("/comment/{comment_id}/")
    @ResponseStatus(HttpStatus.OK)
    fun deleteComment(
        @CurrentUser user: User,
        @PathVariable comment_id: Long
    ): CommentDto.Response {
        val comment = commentService.findById(comment_id)
        commentService.deleteComment(comment, user)
        return CommentDto.Response(comment)
    }

    @PostMapping("/{answer_id}/vote/")
    @ResponseStatus(HttpStatus.CREATED)
    fun addVote(
        @CurrentUser user: User,
        @PathVariable answer_id: Long,
        @Valid @RequestBody requestBody: VoteDto.Request
    ): VoteDto.Response {
        val answer = answerService.findById(answer_id)
        return voteService.addAnswerVote(requestBody, user, answer)
    }
}
