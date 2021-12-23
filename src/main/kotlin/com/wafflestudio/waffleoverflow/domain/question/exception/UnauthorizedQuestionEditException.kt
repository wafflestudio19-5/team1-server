package com.wafflestudio.waffleoverflow.domain.question.exception

import com.wafflestudio.waffleoverflow.global.common.exception.ErrorType
import com.wafflestudio.waffleoverflow.global.common.exception.InvalidRequestException

class UnauthorizedQuestionEditException(detail: String) :
    InvalidRequestException(errorType = ErrorType.NOT_ALLOWED, detail)
