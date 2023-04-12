package la.shiro.birdapi.dal

import la.shiro.birdapi.model.entity.Category
import org.babyfish.jimmer.spring.repository.KRepository
import org.springframework.stereotype.Repository

/**
 * <p>
 * CategoryRepository 接口
 * </p>
 *
 * @author Rin Shiro
 * @date 2023-04-07
 */
@Repository
interface CategoryRepository : KRepository<Category, Long> {
    fun getCategoriesByPid(pid: Long): List<Category>
}

