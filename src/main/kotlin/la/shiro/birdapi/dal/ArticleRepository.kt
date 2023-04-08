package la.shiro.birdapi.dal

import la.shiro.birdapi.model.entity.Article
import la.shiro.birdapi.model.entity.id
import la.shiro.birdapi.model.entity.likeCount
import la.shiro.birdapi.model.entity.viewCount
import org.babyfish.jimmer.spring.repository.KRepository
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.babyfish.jimmer.sql.kt.ast.expression.plus
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
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
    fun findByTitleContainingOrderByIdDesc(
        title: String?,
        pageable: Pageable
    ): Page<Article>

    fun findByCategoryIdOrderByIdDesc(
        categoryId: Long,
        pageable: Pageable
    ): Page<Article>

    fun findTop10ByOrderByViewCountDesc(
    ): List<Article>

    fun updateViewCountById(
        id: Long,
    ): Boolean {
        return sql.createUpdate(Article::class) {
            set(table.viewCount, table.viewCount.plus(1))
            where(table.id eq id)
        }.execute() == 1
    }

    fun updateLikeCountById(
        id: Long,
    ): Boolean {
        return sql.createUpdate(Article::class) {
            set(table.likeCount, table.likeCount.plus(1))
            where(table.id eq id)
        }.execute() == 1
    }
}

