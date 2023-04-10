package la.shiro.birdapi.bll.controller

import la.shiro.birdapi.bll.service.UserService
import la.shiro.birdapi.model.common.DEFAULT_PAGE_INDEX
import la.shiro.birdapi.model.common.DEFAULT_PAGE_SIZE
import la.shiro.birdapi.model.entity.User
import la.shiro.birdapi.model.enums.ResponseCodeEnum
import la.shiro.birdapi.model.input.UserInput
import la.shiro.birdapi.util.ApiResponse
import la.shiro.birdapi.util.ResponseWrapper
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

/**
 *  author: Rin Shiro
 *  Date: 23/4/10 10:41
 *  Description :
 */
@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService
) {
    @GetMapping
    fun getUsers(
        @RequestParam(defaultValue = DEFAULT_PAGE_INDEX) pageIndex: Int,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) pageSize: Int
    ): ApiResponse<Page<User>> {
        return ResponseWrapper.success(userService.getUsers(PageRequest.of(pageIndex, pageSize)))
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long?): ApiResponse<User> {
        return ResponseWrapper.success(userService.getUserById(id))
    }

    @GetMapping("/ids")
    fun getUserByIds(@RequestParam ids: List<Long>?): ApiResponse<List<User>> {
        return ResponseWrapper.success(userService.getUserByIds(ids))
    }

    @GetMapping("/username/{username}")
    fun getUserByUsername(@PathVariable username: String?): ApiResponse<User> {
        return ResponseWrapper.success(userService.getUserByUsername(username))
    }

    @GetMapping("/email/{email}")
    fun getUserByEmail(@PathVariable email: String?): ApiResponse<User> {
        return ResponseWrapper.success(userService.getUserByEmail(email))
    }

    @GetMapping("/phone/{phone}")
    fun getUserByPhone(@PathVariable phone: String?): ApiResponse<User> {
        return ResponseWrapper.success(userService.getUserByPhone(phone))
    }

    @PostMapping("/add")
    fun addUser(@RequestBody userInput: UserInput): ApiResponse<Any?> {
        val result = userService.addUser(userInput)
        return if (result is String) {
            ResponseWrapper.error(result, ResponseCodeEnum.BAD_REQUEST.code)
        } else {
            ResponseWrapper.success("注册成功", ResponseCodeEnum.CREATED.code, result)
        }
    }

    @PutMapping("/id/{id}")
    fun updateUserById(
        @PathVariable id: Long?,
        @RequestBody userInput: UserInput
    ): ApiResponse<Boolean> {
        return ResponseWrapper.success(userService.updateUserById(id, userInput))
    }

    @PutMapping("/id/{id}/username/{username}")
    fun updateByIdAndUsername(
        @PathVariable id: Long?,
        @PathVariable username: String?
    ): ApiResponse<Boolean> {
        return ResponseWrapper.success(userService.updateUsernameById(id, username))
    }

    @PutMapping("/id/{id}/password/{password}")
    fun updateByIdAndPassword(
        @PathVariable id: Long?,
        @PathVariable password: String?
    ): ApiResponse<Boolean> {
        return ResponseWrapper.success(userService.updatePasswordById(id, password))
    }

    @PutMapping("/id/{id}/email/{email}")
    fun updateByIdAndEmail(
        @PathVariable id: Long?,
        @PathVariable email: String?
    ): ApiResponse<Boolean> {
        return ResponseWrapper.success(userService.updateEmailById(id, email))
    }

    @PutMapping("/id/{id}/phone/{phone}")
    fun updateByIdAndPhone(
        @PathVariable id: Long?,
        @PathVariable phone: String?
    ): ApiResponse<Boolean> {
        return ResponseWrapper.success(userService.updatePhoneById(id, phone))
    }

    @PutMapping("/id/{id}/sex/{sex}")
    fun updateByIdAndSex(
        @PathVariable id: Long?,
        @PathVariable sex: String?
    ): ApiResponse<Boolean> {
        return ResponseWrapper.success(userService.updateSexById(id, sex))
    }

    @PutMapping("/id/{id}/wechat/{wechat}")
    fun updateByIdAndWechat(
        @PathVariable id: Long?,
        @PathVariable wechat: String?
    ): ApiResponse<Boolean> {
        return ResponseWrapper.success(userService.updateWechatById(id, wechat))
    }

    @PutMapping("/id/{id}/qq/{qq}")
    fun updateByIdAndQq(
        @PathVariable id: Long?,
        @PathVariable qq: String?
    ): ApiResponse<Boolean> {
        return ResponseWrapper.success(userService.updateQqById(id, qq))
    }

    @PutMapping("/id/{id}/avatar/{avatar}")
    fun updateByIdAndAvatar(
        @PathVariable id: Long?,
        @PathVariable avatar: String?
    ): ApiResponse<Boolean> {
        return ResponseWrapper.success(userService.updateAvatarById(id, avatar))
    }

    @PutMapping("/id/{id}/birthday/{birthday}")
    fun updateByIdAndBirthday(
        @PathVariable id: Long?,
        @PathVariable birthday: LocalDate?
    ): ApiResponse<Boolean> {
        return ResponseWrapper.success(userService.updateBirthdayById(id, birthday))
    }

    @DeleteMapping("/id/{id}")
    fun deleteUserById(@PathVariable id: Long?): ApiResponse<Boolean> {
        return ResponseWrapper.success(userService.deleteUserById(id))
    }

    @DeleteMapping("/ids")
    fun deleteUserByIds(@RequestParam ids: List<Long>?): ApiResponse<Int> {
        return ResponseWrapper.success(userService.deleteUserByIds(ids))
    }

}