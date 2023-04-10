package la.shiro.birdapi.model.input

data class LoginInput(
    val email : String?,
    val phone : String?,
    val password: String?
)