package la.shiro.birdapi.bll.service.impl

import cn.dev33.satoken.stp.StpUtil
import la.shiro.birdapi.bll.service.LoginService
import la.shiro.birdapi.bll.service.UserService
import la.shiro.birdapi.model.entity.User
import la.shiro.birdapi.model.enums.ResponseCodeEnum
import la.shiro.birdapi.util.ApiResponse
import la.shiro.birdapi.util.PasswordUtil
import la.shiro.birdapi.util.ResponseWrapper
import org.springframework.stereotype.Service

/**
 *  author: Rin Shiro
 *  Date: 23/4/10 16:19
 *  Description :
 */

@Service
class LoginServiceImpl(
    private val userService: UserService
) : LoginService {
    override fun login(email: String?, phone: String?, password: String?): ApiResponse<Any> {
        if (email == null && phone == null) {
            return ResponseWrapper.error("Email or Phone must be provided", ResponseCodeEnum.BAD_REQUEST.code)
        }
        if (password == null) {
            return ResponseWrapper.error("Password must be provided", ResponseCodeEnum.BAD_REQUEST.code)
        }
        var user: User? = null
        if (email != null) {
            user = userService.getUserByEmail(email)
        }
        if (phone != null) {
            user = userService.getUserByPhone(phone)
        }
        user ?: return ResponseWrapper.error("User not found", ResponseCodeEnum.NOT_FOUND.code)

        return if (PasswordUtil.match(password, user.password)) {
            StpUtil.login(user.id)
            ResponseWrapper.success("Login success", ResponseCodeEnum.SUCCESS.code, StpUtil.getTokenInfo())
        } else {
            ResponseWrapper.error("Password is incorrect", ResponseCodeEnum.BAD_REQUEST.code)
        }
    }

    override fun logout() {
    }
}