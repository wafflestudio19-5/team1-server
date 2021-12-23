package com.wafflestudio.waffleoverflow.domain.vote.dto

import javax.validation.constraints.Pattern

class VoteDto {
    data class Request(
        @Pattern(regexp = "(up|down)")
        val status: String
    )

//    data class Response(
//
//    )
}
