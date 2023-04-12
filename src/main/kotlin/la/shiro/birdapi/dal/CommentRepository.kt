package la.shiro.birdapi.dal

import la.shiro.birdapi.model.entity.*
import org.babyfish.jimmer.spring.repository.KRepository
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

}

