package com.wafflestudio.waffleoverflow.domain.user.exception

import com.wafflestudio.waffleoverflow.global.common.exception.DataNotFoundException
import com.wafflestudio.waffleoverflow.global.common.exception.ErrorType

class UserNotFoundException(detail: String = "") :
    DataNotFoundException(ErrorType.USER_NOT_FOUND, detail)
