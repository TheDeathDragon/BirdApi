package la.shiro.birdapi.dal

import la.shiro.birdapi.model.entity.ArticleCollection
import org.babyfish.jimmer.spring.repository.KRepository
import org.springframework.stereotype.Repository

/**
 * <p>
 * ArticleCollectionRepository 接口
 * </p>
 *
 * @author Rin Shiro
 * @date 2023-04-07
 */
@Repository
interface ArticleCollectionRepository : KRepository<ArticleCollection, Long> {
    fun findByUserId(userId: Long): List<ArticleCollection>
    fun findByArticleId(articleId: Long): List<ArticleCollection>

}

