package la.shiro.birdapi.util

import la.shiro.birdapi.model.enums.ResponseCodeEnum

/**
 *  author: Rin Shiro
 *  Date: 23/4/8 0:09
 *  Description :
 */
data class ApiResponse<T>(val msg: String, val code: Int, val data: T?)

object ResponseWrapper {
    fun <T> success(data: T? = null): ApiResponse<T> {
        return ApiResponse(ResponseCodeEnum.SUCCESS.msg, ResponseCodeEnum.SUCCESS.code, data)
    }

    fun <T> success(
        msg: String = ResponseCodeEnum.SUCCESS.msg,
        code: Int = ResponseCodeEnum.SUCCESS.code,
        data: T? = null
    ): ApiResponse<T> {
        return ApiResponse(msg, code, data)
    }

    fun <T> error(): ApiResponse<T> {
        return ApiResponse(ResponseCodeEnum.SYSTEM_ERROR.msg, ResponseCodeEnum.SYSTEM_ERROR.code, null)
    }

    fun <T> error(msg: String): ApiResponse<T> {
        return ApiResponse(msg, ResponseCodeEnum.SYSTEM_ERROR.code, null)
    }

    fun <T> error(
        msg: String = ResponseCodeEnum.SYSTEM_ERROR.msg,
        code: Int = ResponseCodeEnum.SYSTEM_ERROR.code,
        data: T? = null
    ): ApiResponse<T> {
        return ApiResponse(msg, code, data)
    }

    fun <T> custom(
        msg: String,
        code: Int,
        data: T? = null
    ): ApiResponse<T> {
        return ApiResponse(msg, code, data)
    }
}