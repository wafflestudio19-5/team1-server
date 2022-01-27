package com.wafflestudio.waffleoverflow.domain.question.api

import com.wafflestudio.waffleoverflow.domain.answer.dto.AnswerDto
import com.wafflestudio.waffleoverflow.domain.answer.repository.AnswerRepository
import com.wafflestudio.waffleoverflow.domain.comment.dto.CommentDto
import com.wafflestudio.waffleoverflow.domain.comment.service.CommentService
import com.wafflestudio.waffleoverflow.domain.question.dto.QuestionDto
import com.wafflestudio.waffleoverflow.domain.question.repository.QuestionRepository
import com.wafflestudio.waffleoverflow.domain.question.service.QuestionService
import com.wafflestudio.waffleoverflow.domain.user.model.User
import com.wafflestudio.waffleoverflow.domain.vote.dto.VoteDto
import com.wafflestudio.waffleoverflow.domain.vote.service.VoteService
import com.wafflestudio.waffleoverflow.global.auth.CurrentUser
import com.wafflestudio.waffleoverflow.global.common.dto.ListResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
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
@RequestMapping("/api/question")
class QuestionController(
    private val questionService: QuestionService,
    private val questionRepository: QuestionRepository,
    private val answerRepository: AnswerRepository,
    private val commentService: CommentService,
    private val voteService: VoteService,
) {
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    fun getQuestions(
        @PageableDefault(size = 15)
        pageable: Pageable,
    ): Page<QuestionDto.Response> {
        return questionRepository.findAll(pageable).map { QuestionDto.Response(it) }
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    fun addQuestion(
        @Valid @RequestBody requestBody: QuestionDto.Request,
        @CurrentUser user: User,
    ): QuestionDto.Response {
        return QuestionDto.Response(
            questionService.addQuestion(requestBody, user)
        )
    }

    @GetMapping("/{question_id}/")
    @ResponseStatus(HttpStatus.OK)
    fun getQuestion(
        @PathVariable question_id: Long,
    ): QuestionDto.Response {
        return QuestionDto.Response(
            questionService.findById(question_id)
        )
    }

    @PutMapping("/{question_id}/")
    @ResponseStatus(HttpStatus.OK)
    fun editQuestion(
        @CurrentUser user: User,
        @Valid @RequestBody requestBody: QuestionDto.Request,
        @PathVariable question_id: Long,
    ): QuestionDto.Response {
        return QuestionDto.Response(
            questionService.editQuestion(requestBody, user, question_id)
        )
    }

    @DeleteMapping("/{question_id}/")
    @ResponseStatus(HttpStatus.OK)
    fun deleteQuestion(
        @CurrentUser user: User,
        @PathVariable question_id: Long,
    ): QuestionDto.Response {
        val question = questionService.findById(question_id)
        questionService.deleteQuestion(user, question)
        return QuestionDto.Response(question)
    }

    @GetMapping("/{question_id}/comment/")
    @ResponseStatus(HttpStatus.OK)
    fun getComments(
        @PathVariable question_id: Long,
    ): ListResponse<CommentDto.Response> {
        return ListResponse(
            questionService.findById(question_id).comments.map { CommentDto.Response(it) }
        )
    }

    @PostMapping("/{question_id}/comment/")
    @ResponseStatus(HttpStatus.CREATED)
    fun addComment(
        @CurrentUser user: User,
        @PathVariable question_id: Long,
        @Valid @RequestBody requestBody: CommentDto.Request,
    ): CommentDto.Response {
        val question = questionService.findById(question_id)
        return CommentDto.Response(
            commentService.addQuestionComment(requestBody, user, question)
        )
    }

    @GetMapping("/{question_id}/answer/")
    @ResponseStatus(HttpStatus.OK)
    fun getAnswers(
        @PathVariable question_id: Long,
        @PageableDefault(size = 15)
        pageable: Pageable,
    ): Page<AnswerDto.Response> {
        return answerRepository.findAnswersByQuestionIdEquals(question_id, pageable).map { AnswerDto.Response(it) }
    }

    @PostMapping("/{question_id}/answer/")
    @ResponseStatus(HttpStatus.CREATED)
    fun addAnswer(
        @CurrentUser user: User,
        @PathVariable question_id: Long,
        @Valid @RequestBody requestBody: AnswerDto.Request,
    ): AnswerDto.Response {
        return questionService.addAnswer(requestBody, user, question_id)
    }

    @PostMapping("/{question_id}/vote/")
    @ResponseStatus(HttpStatus.CREATED)
    fun addVote(
        @CurrentUser user: User,
        @PathVariable question_id: Long,
        @Valid @RequestBody requestBody: VoteDto.Request,
    ): VoteDto.Response {
        return voteService.changeQuestionVote(requestBody, user, question_id)
    }

    @PostMapping("/{question_id}/{answer_id}/accept/")
    @ResponseStatus(HttpStatus.OK)
    fun acceptAnswer(
        @CurrentUser user: User,
        @PathVariable question_id: Long,
        @PathVariable answer_id: Long,
    ): QuestionDto.Response {
        return questionService.acceptAnswer(user, question_id, answer_id)
    }

    @PutMapping("/comment/{comment_id}/")
    @ResponseStatus(HttpStatus.OK)
    fun editComment(
        @CurrentUser user: User,
        @PathVariable comment_id: Long,
        @Valid @RequestBody requestBody: CommentDto.Request,
    ): CommentDto.Response {
        return CommentDto.Response(
            commentService.editComment(requestBody, comment_id, user)
        )
    }

    @DeleteMapping("/comment/{comment_id}/")
    @ResponseStatus(HttpStatus.OK)
    fun deleteComment(
        @CurrentUser user: User,
        @PathVariable comment_id: Long,
    ): CommentDto.Response {
        val comment = commentService.findById(comment_id)
        commentService.deleteComment(comment, user)
        return CommentDto.Response(comment)
    }

    @GetMapping("/search/{keyword}/")
    @ResponseStatus(HttpStatus.OK)
    fun searchQuestions(
        @PageableDefault(size = 15)
        pageable: Pageable,
        @PathVariable keyword: String,
    ): Page<QuestionDto.Response>? {
        return questionRepository.findQuestionsByTitleContainingOrBodyContaining(keyword, keyword, pageable)
            .map { QuestionDto.Response(it) }
    }
}
