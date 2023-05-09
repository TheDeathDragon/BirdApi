package la.shiro.birdapi.bll.service

import la.shiro.birdapi.util.ApiResponse

/**
 *  author: Rin Shiro
 *  Date: 23/4/10 16:18
 *  Description :
 */
interface LoginService {
    fun login(email: String?, phone: String?, password: String?): ApiResponse<Any>

    fun logout():ApiResponse<Any>

    fun getUserInfo(): ApiResponse<Any>
}