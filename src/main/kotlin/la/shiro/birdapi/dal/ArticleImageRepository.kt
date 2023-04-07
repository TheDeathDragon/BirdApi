package la.shiro.birdapi.dal

import la.shiro.birdapi.model.entity.ArticleImage
import org.babyfish.jimmer.spring.repository.KRepository
import org.springframework.stereotype.Repository

/**
 * <p>
 * ArticleImageRepository 接口
 * </p>
 *
 * @author Rin Shiro
 * @date 2023-04-07
 */
@Repository
interface ArticleImageRepository : KRepository<ArticleImage, Long> {

}

