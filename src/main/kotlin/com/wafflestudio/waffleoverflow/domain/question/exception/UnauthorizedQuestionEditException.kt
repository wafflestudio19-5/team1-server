package com.wafflestudio.waffleoverflow.domain.question.exception

import com.wafflestudio.waffleoverflow.global.common.exception.ErrorType
import com.wafflestudio.waffleoverflow.global.common.exception.NotAllowedException

class UnauthorizedQuestionEditException(detail: String) :
    NotAllowedException(errorType = ErrorType.NOT_ALLOWED, detail)
