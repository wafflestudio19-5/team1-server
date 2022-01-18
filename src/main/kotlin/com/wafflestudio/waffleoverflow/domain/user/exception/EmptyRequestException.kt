package com.wafflestudio.waffleoverflow.domain.user.exception

import com.wafflestudio.waffleoverflow.global.common.exception.DataNotFoundException
import com.wafflestudio.waffleoverflow.global.common.exception.ErrorType

class EmptyRequestException(detail: String = "") :
    DataNotFoundException(ErrorType.BAD_REQUEST, detail)
