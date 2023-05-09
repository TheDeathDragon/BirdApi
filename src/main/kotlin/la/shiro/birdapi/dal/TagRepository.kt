package la.shiro.birdapi.dal

import la.shiro.birdapi.model.entity.Tag
import org.babyfish.jimmer.spring.repository.KRepository
import org.springframework.stereotype.Repository

/**
 * <p>
 * TagRepository 接口
 * </p>
 *
 * @author Rin Shiro
 * @date 2023-04-07
 */
@Repository
interface TagRepository : KRepository<Tag, Long>

