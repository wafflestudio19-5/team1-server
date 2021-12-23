package com.wafflestudio.waffleoverflow.domain.answer.dto

import com.wafflestudio.waffleoverflow.domain.answer.model.Answer
import javax.validation.constraints.NotEmpty

class AnswerDto {
    data class Request(
        @NotEmpty
        val body: String
    )

    // By Haneul: You can override this class when you write proper Response dto class for Answer
    // This is a temporary response class to resolve type conflict in QuestionDto.kt
    data class Response(
        val id: Long
    ) {
        constructor(answer: Answer) : this(
            answer.id
        )
    }
}
