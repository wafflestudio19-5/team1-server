package com.wafflestudio.waffleoverflow.domain.question.exception

import com.wafflestudio.waffleoverflow.global.common.exception.ErrorType
import com.wafflestudio.waffleoverflow.global.common.exception.InvalidRequestException

class AcceptedAnswerExistsException(detail: String) :
    InvalidRequestException(errorType = ErrorType.BAD_REQUEST, detail)
