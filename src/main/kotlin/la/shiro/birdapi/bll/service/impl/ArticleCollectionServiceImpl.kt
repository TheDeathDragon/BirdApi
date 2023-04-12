package la.shiro.birdapi.bll.service.impl

import la.shiro.birdapi.bll.service.ArticleCollectionService
import la.shiro.birdapi.dal.ArticleCollectionRepository
import la.shiro.birdapi.model.entity.ArticleCollection
import la.shiro.birdapi.model.input.ArticleCollectionInput
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 *  author: Rin Shiro
 *  Date: 23/4/8 16:26
 *  Description :
 */
@Transactional
@Service
class ArticleCollectionServiceImpl(
    private val articleCollectionRepository: ArticleCollectionRepository
) : ArticleCollectionService {
    override fun getArticleCollections(pageable: Pageable): Page<ArticleCollection>? {
        return articleCollectionRepository.findAll(pageable)
    }

    override fun getArticleCollectionById(id: Long?): ArticleCollection? {
        return id?.let { articleCollectionRepository.findById(id).orElse(null) }
    }

    override fun getArticleCollectionByIds(ids: List<Long>?): List<ArticleCollection>? {
        return ids?.let { articleCollectionRepository.findByIds(it) }
    }

    override fun getArticleCollectionByUserId(userId: Long): List<ArticleCollection>? {
        return articleCollectionRepository.findByUserId(userId)
    }

    override fun getArticleCollectionByArticleId(articleId: Long): List<ArticleCollection>? {
        return articleCollectionRepository.findByArticleId(articleId)
    }

    override fun addArticleCollection(articleCollectionInput: ArticleCollectionInput?): ArticleCollection? {
        return articleCollectionInput?.let { articleCollectionRepository.insert(it) }
    }

    override fun updateArticleCollectionById(
        id: Long,
        articleCollectionInput: ArticleCollectionInput?
    ): ArticleCollection? {
        return articleCollectionInput?.let {
            articleCollectionInput.id = id
            articleCollectionRepository.update(it)
        }
    }

    override fun deleteArticleCollectionById(id: Long): Boolean {
        return if (articleCollectionRepository.existsById(id)) {
            articleCollectionRepository.deleteById(id)
            true
        } else false
    }

    override fun deleteArticleCollectionByIds(ids: List<Long>?): Int {
        return ids?.let {
            var count = 0
            for (id in it) {
                if (articleCollectionRepository.existsById(id)) {
                    articleCollectionRepository.deleteById(id)
                    count++
                }
            }
            count
        } ?: 0
    }

}