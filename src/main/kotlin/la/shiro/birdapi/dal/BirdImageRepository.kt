package la.shiro.birdapi.dal

import la.shiro.birdapi.model.entity.BirdImage
import org.babyfish.jimmer.spring.repository.KRepository
import org.springframework.stereotype.Repository

/**
 * <p>
 * BirdImageRepository 接口
 * </p>
 *
 * @author Rin Shiro
 * @date 2023-04-07
 */
@Repository
interface BirdImageRepository : KRepository<BirdImage, Long> {
    fun findAllByBirdId(birdId: Long): List<BirdImage>
}

