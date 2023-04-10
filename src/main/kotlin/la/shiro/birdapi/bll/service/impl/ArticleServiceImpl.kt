package la.shiro.birdapi.bll.service.impl

import la.shiro.birdapi.bll.service.ArticleService
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
    private val articleRepository: ArticleRepository
) : ArticleService {
    override fun getArticleById(id: Long?): Article? {
        val article = id?.let {
            articleRepository.findById(it).orElse(null)
        }
        article?.let {
            articleRepository.updateViewCountById(it.id)
        }
        return article
    }

    override fun getArticleByIds(ids: List<Long>?): List<Article>? {
        return ids?.let { articleRepository.findByIds(it) }
    }

    override fun getArticleByTitle(title: String?, pageable: Pageable): Page<Article>? {
        return articleRepository.findByTitleContainingOrderByIdDesc(title, pageable)
    }

    override fun getArticles(pageable: Pageable): Page<Article>? {
        return articleRepository.findAll(pageable)
    }

    override fun getArticlesByCategoryId(categoryId: Long?, pageable: Pageable): Page<Article>? {
        return categoryId?.let { articleRepository.findByCategoryIdOrderByIdDesc(it, pageable) }
    }

    override fun getHotArticles(): List<Article>? {
        return articleRepository.findTop10ByOrderByViewCountDesc()
    }

    override fun addArticle(articleInput: ArticleInput?): Article? {
        return articleInput?.let {
            println(it)
            articleRepository.insert(it)
        }
    }

    override fun updateArticleById(id: Long?, articleInput: ArticleInput?): Article? {
        return id?.let {
            articleInput?.let {
                articleInput.id = id
                articleRepository.update(articleInput)
            }
        }
    }

    override fun updateArticleLike(id: Long?): Boolean {
        id?.let {
            return articleRepository.updateLikeCountById(it)
        } ?: return false
    }

    override fun deleteArticleById(id: Long?): Boolean {
        return id?.let {
            if (articleRepository.existsById(it)) {
                articleRepository.deleteById(it)
                true
            } else false
        } ?: false
    }

    override fun deleteArticleByIds(ids: List<Long>?): Int {
        return ids?.let {
            var count = 0
            it.forEach { id ->
                if (articleRepository.existsById(id)) {
                    articleRepository.deleteById(id)
                    count++
                }
            }
            count
        } ?: 0
    }
}