package la.shiro.birdapi.bll.service.impl.impl

import la.shiro.birdapi.bll.service.impl.ArticleService
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
        return id?.let { articleRepository.findById(it).orElse(null) }
    }

    override fun getArticleByIds(ids: List<Long?>?): List<Article?>? {
        TODO("Not yet implemented")
    }

    override fun getArticleByTitle(title: String?, pageable: Pageable?): Page<Article?>? {
        TODO("Not yet implemented")
    }

    override fun getArticles(pageable: Pageable?): Page<Article?>? {
        TODO("Not yet implemented")
    }

    override fun getArticlesByCategoryId(categoryId: Long?, pageable: Pageable?): Page<Article?>? {
        TODO("Not yet implemented")
    }

    override fun getHotArticles(): List<Article?>? {
        TODO("Not yet implemented")
    }

    override fun addArticle(articleInput: ArticleInput?): Article? {
        TODO("Not yet implemented")
    }

    override fun updateArticleById(id: Long?, articleInput: ArticleInput?): Article? {
        TODO("Not yet implemented")
    }

    override fun updateArticleLike(id: Long?): Boolean {
        TODO("Not yet implemented")
    }

    override fun deleteArticleById(id: Long?): Boolean {
        TODO("Not yet implemented")
    }

    override fun deleteArticleByIds(ids: List<Long?>?): Int {
        TODO("Not yet implemented")
    }
}