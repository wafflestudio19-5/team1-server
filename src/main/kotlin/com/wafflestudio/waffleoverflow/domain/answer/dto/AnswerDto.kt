package com.wafflestudio.waffleoverflow.domain.answer.dto

import com.wafflestudio.waffleoverflow.domain.answer.model.Answer

class AnswerDto {
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
