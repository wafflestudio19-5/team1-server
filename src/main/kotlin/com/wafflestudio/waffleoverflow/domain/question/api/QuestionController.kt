package com.wafflestudio.waffleoverflow.domain.question.api

import com.wafflestudio.waffleoverflow.domain.question.dto.QuestionDto
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/question")
class QuestionController() {
    @GetMapping("/{question_id}")
    @ResponseStatus(HttpStatus.OK)
    fun getQuestion(
        @Valid @RequestBody body: RequestBody,
    ): QuestionDto.Response {
        return QuestionDto.Response(null)
    }
}
