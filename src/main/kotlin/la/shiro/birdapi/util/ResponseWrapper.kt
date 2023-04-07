package la.shiro.birdapi.util

/**
 *  author: Rin Shiro
 *  Date: 23/4/8 0:09
 *  Description :
 */
data class ResponseWrapper(private var code: Int, private var message: String, private var data: Any?) {
    companion object {
        fun success(data: Any?): ResponseWrapper {
            return ResponseWrapper(200, "success", data)
        }

        fun success(data: Any?, code: Int): ResponseWrapper {
            return ResponseWrapper(code, "success", data)
        }

        fun success(data: Any?, message: String): ResponseWrapper {
            return ResponseWrapper(200, message, data)
        }

        fun success(code: Int, message: String): ResponseWrapper {
            return ResponseWrapper(code, message, null)
        }

        fun success(data: Any?, code: Int, message: String): ResponseWrapper {
            return ResponseWrapper(code, message, data)
        }

        fun error(): ResponseWrapper {
            return ResponseWrapper(500, "error", null)
        }

        fun error(message: String): ResponseWrapper {
            return ResponseWrapper(500, message, null)
        }

        fun error(data: Any?): ResponseWrapper {
            return ResponseWrapper(500, "error", data)
        }

        fun error(data: Any?, code: Int): ResponseWrapper {
            return ResponseWrapper(code, "error", data)
        }

        fun error(data: Any?, message: String): ResponseWrapper {
            return ResponseWrapper(500, message, data)
        }

        fun error(code: Int, message: String): ResponseWrapper {
            return ResponseWrapper(code, message, null)
        }

        fun error(data: Any?, code: Int, message: String): ResponseWrapper {
            return ResponseWrapper(code, message, data)
        }
    }
}