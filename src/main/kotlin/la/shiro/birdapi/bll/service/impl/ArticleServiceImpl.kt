package la.shiro.birdapi.bll.service.impl

import la.shiro.birdapi.bll.service.ArticleService
import la.shiro.birdapi.dal.ArticleImageRepository
import la.shiro.birdapi.dal.ArticleRepository
import la.shiro.birdapi.model.entity.Article
import la.shiro.birdapi.model.input.ArticleInput
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 *  author: Rin Shiro
 *  Date: 23/4/8 1:21
 *  Description :
 */
@Transactional
@Service
class ArticleServiceImpl(
    private val articleRepository: ArticleRepository,
    private val articleImageRepository: ArticleImageRepository
) : ArticleService {
    override fun getArticleById(id: Long): Article? {
        val article = articleRepository.findById(id).orElse(null)
        article?.let {
            articleRepository.updateViewCountById(it.id)
        }
        return article
    }

    override fun getArticleByIds(ids: List<Long>?): List<Article>? {
        return ids?.let { articleRepository.findByIds(it) }
    }

    override fun getArticleByTitle(title: String?, pageable: Pageable): Page<Article> {
        return articleRepository.findByTitleContainingOrderByIdDesc(title, pageable)
    }

    override fun getArticles(pageable: Pageable): Page<Article> {
        return articleRepository.findAll(pageable)
    }

    override fun getArticlesCondition(pageable: Pageable,articleInput: ArticleInput?): Page<Article> {
        return articleRepository.findArticlesByCondition(pageable,articleInput)
    }

    override fun getArticlesByCategoryId(categoryId: Long, pageable: Pageable): Page<Article>? {
        return articleRepository.findByCategoryIdOrderByIdDesc(categoryId, pageable)
    }

    override fun getHotArticles(): List<Article>? {
        return articleRepository.findTop10ByOrderByViewCountDesc()
    }

    override fun addArticle(articleInput: ArticleInput?): Article? {
        return articleInput?.let {
            articleRepository.insert(it)
        }
    }

    override fun updateArticleById(id: Long, articleInput: ArticleInput?): Article? {
        return articleInput?.let {
            articleInput.id = id
            articleRepository.update(articleInput)
        }
    }

    override fun updateArticleLike(id: Long): Boolean {
        return articleRepository.updateLikeCountById(id)
    }

    override fun deleteArticleById(id: Long): Boolean {
        return if (articleRepository.existsById(id)) {
            articleRepository.deleteById(id)
            articleImageRepository.deleteArticleImageByArticleId(id)
            return true
        } else false
    }

    override fun deleteArticleByIds(ids: List<Long>?): Int {
        return ids?.let {
            var count = 0
            it.forEach { id ->
                if (articleRepository.existsById(id)) {
                    articleRepository.deleteById(id)
                    articleImageRepository.deleteArticleImageByArticleId(id)
                    count++
                }
            }
            count
        } ?: 0
    }
}