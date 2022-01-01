package com.wafflestudio.waffleoverflow.domain.question.exception

import com.wafflestudio.waffleoverflow.global.common.exception.DataNotFoundException
import com.wafflestudio.waffleoverflow.global.common.exception.ErrorType

class QuestionNotFoundException(detail: String) : DataNotFoundException(errorType = ErrorType.DATA_NOT_FOUND, detail)
