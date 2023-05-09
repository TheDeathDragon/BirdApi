package la.shiro.birdapi.dal

import la.shiro.birdapi.model.entity.*
import la.shiro.birdapi.model.input.CommentInput
import org.babyfish.jimmer.spring.repository.KRepository
import org.babyfish.jimmer.sql.kt.ast.expression.desc
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.babyfish.jimmer.sql.kt.ast.expression.plus
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

/**
 * <p>
 * CommentRepository 接口
 * </p>
 *
 * @author Rin Shiro
 * @date 2023-04-07
 */
@Repository
interface CommentRepository : KRepository<Comment, Long> {
    fun findAllByArticleId(articleId: Long, pageable: Pageable): Page<Comment>

    fun findAllByUserId(userId: Long, pageable: Pageable): Page<Comment>

    fun findAllByPid(pid: Long, pageable: Pageable): Page<Comment>

    fun findAllByPublished(published: String, pageable: Pageable): Page<Comment>

    fun updateLikeCountById(id: Long): Boolean {
        return sql.createUpdate(Comment::class) {
            set(table.likeCount, table.likeCount.plus(1))
            where(table.id eq id)
        }.execute() == 1
    }

    fun updatePublishedById(id: Long, published: String): Boolean {
        return sql.createUpdate(Comment::class) {
            set(table.published, published)
            where(table.id eq id)
        }.execute() == 1
    }

    fun deleteCommentsByArticleId(articleId: Long): Long {
        val originCount = this.count()
        sql.createDelete(Comment::class) {
            where(table.articleId eq articleId)
        }.execute()
        return originCount - this.count()
    }

    fun deleteCommentsByUserId(userId: Long): Long {
        val originCount = this.count()
        sql.createDelete(Comment::class) {
            where(table.userId eq userId)
        }.execute()
        return originCount - this.count()
    }

    fun deleteCommentsByPid(pid: Long): Long {
        val originCount = this.count()
        sql.createDelete(Comment::class) {
            where(table.pid eq pid)
        }.execute()
        return originCount - this.count()
    }

    fun findCommentsCondition(pageable: Pageable, commentInput: CommentInput?): Page<Comment> {
        if (commentInput == null) {
            return findAll(pageable)
        }

        val articleId = commentInput.articleId
        val userId = commentInput.userId
        val pid = commentInput.pid
        val published = commentInput.published

        return pager(pageable).execute(
            sql.createQuery(Comment::class) {
                articleId?.takeIf { it > 0 }?.let {
                    where(table.articleId eq articleId)
                }
                userId?.takeIf { it > 0 }?.let {
                    where(table.userId eq userId)
                }
                pid?.takeIf { it > 0 }?.let {
                    where(table.pid eq pid)
                }
                published?.takeIf { it.isNotBlank() }?.let {
                    where(table.published eq published)
                }
                orderBy(table.id.desc())
                select(table)
            }
        )
    }

}

