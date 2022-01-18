package com.wafflestudio.waffleoverflow.domain.answer.exception

import com.wafflestudio.waffleoverflow.global.common.exception.DataNotFoundException
import com.wafflestudio.waffleoverflow.global.common.exception.ErrorType

class AnswerNotFoundException(detail: String) : DataNotFoundException(errorType = ErrorType.DATA_NOT_FOUND, detail)
