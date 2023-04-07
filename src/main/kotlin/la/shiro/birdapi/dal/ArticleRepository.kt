package la.shiro.birdapi.dal

import la.shiro.birdapi.model.entity.Article
import org.babyfish.jimmer.spring.repository.KRepository
import org.springframework.stereotype.Repository

/**
 * <p>
 * ArticleRepository 接口
 * </p>
 *
 * @author Rin Shiro
 * @date 2023-04-07
 */
@Repository
interface ArticleRepository : KRepository<Article, Long> {

}

