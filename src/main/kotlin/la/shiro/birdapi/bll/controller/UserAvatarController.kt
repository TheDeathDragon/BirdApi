package la.shiro.birdapi.bll.controller

import la.shiro.birdapi.bll.service.UserAvatarService
import la.shiro.birdapi.model.common.DEFAULT_PAGE_INDEX
import la.shiro.birdapi.model.common.DEFAULT_PAGE_SIZE
import la.shiro.birdapi.model.common.DEFAULT_PAGE_SORT_RULE
import la.shiro.birdapi.model.entity.UserAvatar
import la.shiro.birdapi.util.ApiResponse
import la.shiro.birdapi.util.ResponseWrapper
import org.babyfish.jimmer.spring.model.SortUtils
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

/**
 *  author: Rin Shiro
 *  Date: 23/4/12 17:22
 *  Description :
 */
@RestController
@RequestMapping("/avatar")
class UserAvatarController(
    private val userAvatarService: UserAvatarService
) {

    @GetMapping
    fun getUserAvatars(
        @RequestParam(defaultValue = DEFAULT_PAGE_INDEX) pageIndex: Int,
        @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) pageSize: Int,
        @RequestParam(defaultValue = DEFAULT_PAGE_SORT_RULE) sortCode: String
    ): ApiResponse<Page<UserAvatar>> {
        return ResponseWrapper.success(
            userAvatarService.getUserAvatars(
                PageRequest.of(
                    pageIndex, pageSize, SortUtils.toSort(sortCode)
                )
            )
        )
    }

    @GetMapping("/{id}")
    fun getUserAvatarById(@PathVariable id: Long): ApiResponse<UserAvatar> {
        return ResponseWrapper.success(userAvatarService.getUserAvatarById(id))
    }

    @GetMapping("/user/{userId}")
    fun getUserAvatarByUserId(@PathVariable userId: Long): ApiResponse<UserAvatar> {
        return ResponseWrapper.success(userAvatarService.getUserAvatarByUserId(userId))
    }

    @PostMapping(
        value = ["/upload"],
        consumes = ["multipart/form-data"],
        produces = ["application/json"]
    )
    fun addUserAvatar(
        @RequestParam userId: Long,
        @RequestPart(value = "file") file: MultipartFile
    ): ApiResponse<UserAvatar> {
        if (file.isEmpty) {
            return ResponseWrapper.error("File is empty")
        }
        return ResponseWrapper.success(userAvatarService.addUserAvatar(userId, file))
    }

    @PutMapping(
        value = ["/id"],
        consumes = ["multipart/form-data"],
        produces = ["application/json"]
    )
    fun updateUserAvatarById(
        @RequestParam id: Long,
        @RequestPart(value = "file") file: MultipartFile
    ): ApiResponse<UserAvatar> {
        if (file.isEmpty) {
            return ResponseWrapper.error("File is empty")
        }
        return ResponseWrapper.success(userAvatarService.updateUserAvatarById(id, file))
    }

    @PutMapping(
        value = ["/userId"],
        consumes = ["multipart/form-data"],
        produces = ["application/json"]
    )
    fun updateUserAvatarByUserId(
        @RequestParam userId: Long,
        @RequestPart(value = "file") file: MultipartFile
    ): ApiResponse<UserAvatar> {
        if (file.isEmpty) {
            return ResponseWrapper.error("File is empty")
        }
        return ResponseWrapper.success(userAvatarService.updateUserAvatarByUserId(userId, file))
    }

    @DeleteMapping("/{id}")
    fun deleteUserAvatarById(@PathVariable id: Long): ApiResponse<Boolean> {
        return ResponseWrapper.success(userAvatarService.deleteUserAvatarById(id))
    }

    @DeleteMapping("/user/{userId}")
    fun deleteUserAvatarByUserId(@PathVariable userId: Long): ApiResponse<Boolean> {
        return ResponseWrapper.success(userAvatarService.deleteUserAvatarByUserId(userId))
    }

    @DeleteMapping("/ids")
    fun deleteUserAvatarByIds(@RequestParam ids: List<Long>?): ApiResponse<Int> {
        return ResponseWrapper.success(userAvatarService.deleteUserAvatarByIds(ids))
    }

}