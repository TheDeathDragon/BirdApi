package la.shiro.birdapi.dal

import la.shiro.birdapi.model.entity.*
import la.shiro.birdapi.model.input.ArticleInput
import org.babyfish.jimmer.spring.repository.KRepository
import org.babyfish.jimmer.sql.kt.ast.expression.*
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
    ): List<Article>?

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

    fun findArticlesByCondition(pageable: Pageable, articleInput: ArticleInput?): Page<Article> {

        if (articleInput == null) {
            return findAll(pageable)
        }

        val title = articleInput.title
        val categoryId = articleInput.categoryId
        val userID = articleInput.userId
        val status = articleInput.status
        val comment = articleInput.comment
        val top = articleInput.top

        return pager(pageable).execute(
            sql.createQuery(Article::class) {
                title?.takeIf { it.isNotBlank() }?.let {
                    where(table.title ilike it)
                }
                categoryId?.takeIf {
                    it > 0
                }?.let {
                    where(table.categoryId eq it)
                }
                userID?.takeIf {
                    it > 0
                }?.let {
                    where(table.userId eq it)
                }
                status?.takeIf {
                    it.isNotBlank()
                }?.let {
                    where(table.status eq it)
                }
                comment?.takeIf {
                    it.isNotBlank()
                }?.let {
                    where(table.comment eq it)
                }
                top?.takeIf {
                    it.isNotBlank()
                }?.let {
                    where(table.top eq it)
                }
                orderBy(table.id.desc())
                select(table)
            })
    }

}

