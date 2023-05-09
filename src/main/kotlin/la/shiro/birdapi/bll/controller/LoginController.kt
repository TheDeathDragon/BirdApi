package la.shiro.birdapi.bll.controller

import la.shiro.birdapi.bll.service.LoginService
import la.shiro.birdapi.model.input.LoginInput
import la.shiro.birdapi.util.ApiResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

/**
 *  author: Rin Shiro
 *  Date: 23/4/10 14:49
 *  Description :
 */
@RestController
class LoginController(
    private val loginService: LoginService
) {

    @PostMapping("/login")
    fun userLogin(
        @RequestBody loginInput: LoginInput
    ): ApiResponse<Any> {
        val (email, phone, password) = loginInput
        return loginService.login(email, phone, password)
    }

    @PostMapping("/logout")
    fun userLogout(): ApiResponse<Any> {
        return loginService.logout()
    }

    @GetMapping("/info")
    fun getUserInfo(): ApiResponse<Any> {
        return loginService.getUserInfo()
    }
}