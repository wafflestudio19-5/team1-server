package com.wafflestudio.waffleoverflow.domain.answer.dto

import javax.validation.constraints.NotEmpty

class AnswerDto {
    data class Request(
        @NotEmpty
        val body: String
    )
}
