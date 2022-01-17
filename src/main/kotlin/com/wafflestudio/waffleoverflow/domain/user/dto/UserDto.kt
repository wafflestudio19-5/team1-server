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
        val accessToken: String,
        val image: String?,
        val questions: List<QuestionDto.ResponseSummary>,
        val answers: List<AnswerDto.ResponseSummary>,
        val location: String?,
        val userTitle: String?,
        val aboutMe: String?,
        val websiteLink: String?,
        val githubLink: String?,
    ) {
        constructor(user: User) : this(
            id = user.id,
            email = user.email,
            username = user.username,
            accessToken = user.accessToken,
            image = user.s3Path,
            questions = user.questions.map { QuestionDto.ResponseSummary(it) },
            answers = user.answers.map { AnswerDto.ResponseSummary(it) },
            location = user.location,
            userTitle = user.userTitle,
            aboutMe = user.aboutMe,
            websiteLink = user.websiteLink,
            githubLink = user.githubLink,
        )
    }

    data class ResponseSummary(
        val id: Long,
        val username: String
    ) {
        constructor(user: User) : this(
            id = user.id,
            username = user.username
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

    data class EditProfileRequest(
        val location: String?,
        val userTitle: String?,
        val aboutMe: String?,
        val websiteLink: String?,
        val githubLink: String?,
    )
}
