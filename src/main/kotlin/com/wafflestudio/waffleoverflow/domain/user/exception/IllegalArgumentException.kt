package com.wafflestudio.waffleoverflow.domain.user.exception

import com.wafflestudio.waffleoverflow.global.common.exception.ErrorType
import com.wafflestudio.waffleoverflow.global.common.exception.InvalidRequestException

class IllegalArgumentException(detail: String = "") :
    InvalidRequestException(ErrorType.INVALID_REQUEST, detail = detail)
