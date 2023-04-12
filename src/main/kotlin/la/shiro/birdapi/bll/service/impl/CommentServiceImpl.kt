package la.shiro.birdapi.bll.service.impl

import la.shiro.birdapi.bll.service.CommentService
import la.shiro.birdapi.dal.CommentRepository
import la.shiro.birdapi.model.entity.Comment
import la.shiro.birdapi.model.input.CommentInput
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 *  author: Rin Shiro
 *  Date: 23/4/12 13:57
 *  Description :
 */
@Transactional
@Service
class CommentServiceImpl(
    private val commentRepository: CommentRepository,
) : CommentService {
    override fun getComments(pageable: Pageable): Page<Comment> {
        return commentRepository.findAll(pageable)
    }

    override fun getCommentById(id: Long): Comment? {
        return commentRepository.findById(id).orElse(null)
    }

    override fun getCommentsByArticleId(articleId: Long, pageable: Pageable): Page<Comment> {
        return commentRepository.findAllByArticleId(articleId, pageable)
    }

    override fun getCommentsByUserId(userId: Long, pageable: Pageable): Page<Comment> {
        return commentRepository.findAllByUserId(userId, pageable)
    }

    override fun getCommentsByPid(pid: Long, pageable: Pageable): Page<Comment> {
        return commentRepository.findAllByPid(pid, pageable)
    }

    override fun getCommentsByPublished(published: String, pageable: Pageable): Page<Comment> {
        return commentRepository.findAllByPublished(published, pageable)
    }

    override fun addComment(commentInput: CommentInput): Comment {
        return commentRepository.insert(commentInput)
    }

    override fun updateCommentById(id: Long, commentInput: CommentInput): Comment {
        commentInput.id = id
        return commentRepository.update(commentInput)
    }

    override fun updateCommentLikeCountById(id: Long): Boolean {
        return commentRepository.updateLikeCountById(id)
    }

    override fun updateCommentPublishedById(id: Long, published: String): Boolean {
        return commentRepository.updatePublishedById(id, published)
    }

    override fun deleteCommentById(id: Long): Boolean {
        return if (commentRepository.existsById(id)) {
            commentRepository.deleteById(id)
            true
        } else false
    }

    override fun deleteCommentsByArticleId(articleId: Long): Long {
        return commentRepository.deleteCommentsByArticleId(articleId)
    }

    override fun deleteCommentsByUserId(userId: Long): Long {
        return commentRepository.deleteCommentsByUserId(userId)
    }

    override fun deleteCommentsByPid(pid: Long): Long {
        return commentRepository.deleteCommentsByPid(pid)
    }

    override fun deleteCommentsByIds(ids: List<Long>?): Int {
        var count = 0
        ids?.forEach {
            if (commentRepository.existsById(it)) {
                commentRepository.deleteById(it)
                count++
            }
        }
        return count
    }

}