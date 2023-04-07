package la.shiro.birdapi.dal

import la.shiro.birdapi.model.entity.User
import org.babyfish.jimmer.spring.repository.KRepository
import org.springframework.stereotype.Repository

/**
 * <p>
 * UserRepository 接口
 * </p>
 *
 * @author Rin Shiro
 * @date 2023-04-07
 */
@Repository
interface UserRepository : KRepository<User, Long> {

}

