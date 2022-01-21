package com.wafflestudio.waffleoverflow.domain.user.exception

import com.wafflestudio.waffleoverflow.global.common.exception.ErrorType
import com.wafflestudio.waffleoverflow.global.common.exception.InvalidRequestException

class InvalidEmailFormatException(detail: String = "") :
    InvalidRequestException(ErrorType.BAD_REQUEST, detail)
