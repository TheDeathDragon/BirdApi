package la.shiro.birdapi.bll.service.impl

import la.shiro.birdapi.bll.service.UserAvatarService
import la.shiro.birdapi.dal.UserAvatarRepository
import la.shiro.birdapi.model.common.DEFAULT_AVATAR_UPLOAD_PATH
import la.shiro.birdapi.model.entity.UserAvatar
import la.shiro.birdapi.model.input.UserAvatarInput
import la.shiro.birdapi.util.FileUtils
import la.shiro.birdapi.util.ImageUtil
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

/**
 *  author: Rin Shiro
 *  Date: 23/4/12 16:14
 *  Description :
 */
@Service
@Transactional
class UserAvatarServiceImpl(
    private val userAvatarRepository: UserAvatarRepository
) : UserAvatarService {
    override fun getUserAvatars(pageable: Pageable): Page<UserAvatar> {
        return userAvatarRepository.findAll(pageable)
    }

    override fun getUserAvatarById(id: Long): UserAvatar? {
        return userAvatarRepository.findById(id).orElse(null)
    }

    override fun getUserAvatarByUserId(userId: Long): UserAvatar? {
        return userAvatarRepository.findByUserId(userId)
    }

    override fun addUserAvatar(userId: Long, file: MultipartFile): UserAvatar? {
        val path = FileUtils.uploadFile(file, DEFAULT_AVATAR_UPLOAD_PATH)
        val url = ImageUtil.getImgUrl(path)
        val userAvatarInput = UserAvatarInput(
            id = null,
            userId = userId,
            url = url,
            path = path
        )
        return userAvatarRepository.insert(userAvatarInput)
    }

    override fun updateUserAvatarById(id: Long, file: MultipartFile): UserAvatar? {
        val userAvatar = userAvatarRepository.findById(id).orElse(null) ?: return null
        FileUtils.deleteFile(userAvatar.path)
        val path = FileUtils.uploadFile(file, DEFAULT_AVATAR_UPLOAD_PATH)
        val url = ImageUtil.getImgUrl(path)
        val userAvatarInput = UserAvatarInput(
            id = userAvatar.id,
            userId = userAvatar.userId,
            url = url,
            path = path
        )
        return userAvatarRepository.update(userAvatarInput)
    }

    override fun updateUserAvatarByUserId(userId: Long, file: MultipartFile): UserAvatar? {
        val userAvatar = userAvatarRepository.findByUserId(userId) ?: return null
        FileUtils.deleteFile(userAvatar.path)
        val path = FileUtils.uploadFile(file, DEFAULT_AVATAR_UPLOAD_PATH)
        val url = ImageUtil.getImgUrl(path)
        val userAvatarInput = UserAvatarInput(
            id = userAvatar.id,
            userId = userAvatar.userId,
            url = url,
            path = path
        )
        return userAvatarRepository.update(userAvatarInput)
    }

    override fun deleteUserAvatarById(id: Long): Boolean {
        return if (userAvatarRepository.existsById(id)) {
            userAvatarRepository.deleteById(id)
            true
        } else false
    }

    override fun deleteUserAvatarByUserId(userId: Long): Boolean {
        val userAvatar = userAvatarRepository.findByUserId(userId)
        return if (userAvatar != null) {
            userAvatarRepository.delete(userAvatar)
            true
        } else false
    }

    override fun deleteUserAvatarByIds(ids: List<Long>?): Int {
        var count = 0
        ids?.forEach {
            if (userAvatarRepository.existsById(it)) {
                userAvatarRepository.deleteById(it)
                count++
            }
        }
        return count
    }

}