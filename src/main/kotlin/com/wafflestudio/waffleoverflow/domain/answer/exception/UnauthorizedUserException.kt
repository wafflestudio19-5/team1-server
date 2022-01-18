package com.wafflestudio.waffleoverflow.domain.answer.exception

import com.wafflestudio.waffleoverflow.global.common.exception.ErrorType
import com.wafflestudio.waffleoverflow.global.common.exception.NotAllowedException

class UnauthorizedUserException(detail: String) :
    NotAllowedException(errorType = ErrorType.NOT_ALLOWED, detail)
