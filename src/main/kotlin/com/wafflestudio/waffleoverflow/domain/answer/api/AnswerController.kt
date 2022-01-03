package com.wafflestudio.waffleoverflow.domain.answer.api

import com.wafflestudio.waffleoverflow.domain.answer.dto.AnswerDto
import com.wafflestudio.waffleoverflow.domain.answer.service.AnswerService
import com.wafflestudio.waffleoverflow.domain.comment.service.CommentService
import com.wafflestudio.waffleoverflow.domain.user.model.User
import com.wafflestudio.waffleoverflow.domain.vote.service.VoteService
import com.wafflestudio.waffleoverflow.global.auth.CurrentUser
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
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
}
