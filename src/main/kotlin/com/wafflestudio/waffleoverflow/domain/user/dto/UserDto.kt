package com.wafflestudio.waffleoverflow.domain.user.dto

import com.wafflestudio.waffleoverflow.domain.answer.dto.AnswerDto
import com.wafflestudio.waffleoverflow.domain.question.dto.QuestionDto
import com.wafflestudio.waffleoverflow.domain.user.model.User
import javax.validation.constraints.NotBlank

class UserDto {
    data class Response(
        val id: Long,
        val email: String,
        val username: String,
        val questions: List<QuestionDto.ResponseSummary>,
        val answers: List<AnswerDto.ResponseSummary>
    ) {
        constructor(user: User) : this(
            id = user.id,
            email = user.email,
            username = user.username,
            questions = user.questions.map { QuestionDto.ResponseSummary(it) },
            answers = user.answers.map { AnswerDto.ResponseSummary(it) }
        )
    }

    data class SignupRequest(
        @field:NotBlank
        val email: String,

        @field:NotBlank
        val username: String,

        @field:NotBlank
        val password: String,

        @field:NotBlank
        var grantType: String? = "PASSWORD",
    )
}
