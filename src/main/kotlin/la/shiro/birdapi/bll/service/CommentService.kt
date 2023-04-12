package la.shiro.birdapi.bll.service

import la.shiro.birdapi.model.entity.Comment
import la.shiro.birdapi.model.input.CommentInput
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

/**
 *  author: Rin Shiro
 *  Date: 23/4/12 13:53
 *  Description :
 */
interface CommentService {
    fun getComments(pageable: Pageable): Page<Comment>

    fun getCommentById(id: Long): Comment?

    fun getCommentsByArticleId(articleId: Long, pageable: Pageable): Page<Comment>

    fun getCommentsByUserId(userId: Long, pageable: Pageable): Page<Comment>

    fun getCommentsByPid(pid: Long, pageable: Pageable): Page<Comment>

    fun getCommentsByPublished(published: String, pageable: Pageable): Page<Comment>

    fun addComment(commentInput: CommentInput): Comment

    fun updateCommentById(id: Long, commentInput: CommentInput): Comment

    fun updateCommentLikeCountById(id: Long): Boolean

    fun updateCommentPublishedById(id: Long, published: String): Boolean

    fun deleteCommentById(id: Long): Boolean

    fun deleteCommentsByArticleId(articleId: Long): Long

    fun deleteCommentsByUserId(userId: Long): Long

    fun deleteCommentsByPid(pid: Long): Long

    fun deleteCommentsByIds(ids: List<Long>?): Int


}