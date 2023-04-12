package la.shiro.birdapi.dal

import la.shiro.birdapi.model.entity.Notice
import org.babyfish.jimmer.spring.repository.KRepository
import org.springframework.stereotype.Repository

/**
 * <p>
 * NoticeRepository 接口
 * </p>
 *
 * @author Rin Shiro
 * @date 2023-04-07
 */
@Repository
interface NoticeRepository : KRepository<Notice, Long> {

    fun findTop1ByOrderByIdDesc(): Notice?
}
