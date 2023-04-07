package la.shiro.birdapi.model.enums

enum class ResponseCodeEnum(val code: Int, val msg: String) {
    SUCCESS(200, "OK"),
    CREATED(201, "Created"),
    ACCEPTED(202, "Accepted"),
    NOT_MODIFIED(304, "Not Modified"),
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    SYSTEM_ERROR(500, "Internal Server Error"),
    NOT_IMPLEMENTED(501, "Not Implemented"),
    SERVICE_UNAVAILABLE(503, "Service Unavailable")
}