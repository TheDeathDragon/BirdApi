package la.shiro.birdapi.bll.service

import la.shiro.birdapi.model.entity.UserAvatar
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.multipart.MultipartFile

/**
 *  author: Rin Shiro
 *  Date: 23/4/12 16:06
 *  Description :
 */
interface UserAvatarService {
    fun getUserAvatars(pageable: Pageable): Page<UserAvatar>

    fun getUserAvatarById(id: Long): UserAvatar?

    fun getUserAvatarByUserId(userId: Long): UserAvatar?

    fun addUserAvatar(userId: Long, file: MultipartFile): UserAvatar?

    fun updateUserAvatarById(id: Long, file: MultipartFile): UserAvatar?

    fun updateUserAvatarByUserId(userId: Long, file: MultipartFile): UserAvatar?

    fun deleteUserAvatarById(id: Long): Boolean

    fun deleteUserAvatarByUserId(userId: Long): Boolean

    fun deleteUserAvatarByIds(ids: List<Long>?): Int
}