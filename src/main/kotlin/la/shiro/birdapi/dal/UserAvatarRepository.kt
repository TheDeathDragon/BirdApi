package la.shiro.birdapi.dal

import la.shiro.birdapi.model.entity.UserAvatar
import org.babyfish.jimmer.spring.repository.KRepository
import org.springframework.stereotype.Repository

/**
 * <p>
 * UserAvatarRepository 接口
 * </p>
 *
 * @author Rin Shiro
 * @date 2023-04-07
 */
@Repository
interface UserAvatarRepository : KRepository<UserAvatar, Long> {
    fun findByUserId(userId: Long): UserAvatar?
}

