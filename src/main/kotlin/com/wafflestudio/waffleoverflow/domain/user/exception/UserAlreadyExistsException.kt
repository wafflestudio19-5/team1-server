package com.wafflestudio.waffleoverflow.domain.user.exception

import com.wafflestudio.waffleoverflow.global.common.exception.ConflictException
import com.wafflestudio.waffleoverflow.global.common.exception.ErrorType

class UserAlreadyExistsException(detail: String = "") :
    ConflictException(ErrorType.USER_ALREADY_EXISTS, detail)
