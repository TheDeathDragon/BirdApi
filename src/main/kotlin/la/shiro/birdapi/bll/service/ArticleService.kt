package la.shiro.birdapi.bll.service

import la.shiro.birdapi.model.entity.Article
import la.shiro.birdapi.model.input.ArticleInput
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

/**
 *  author: Rin Shiro
 *  Date: 23/4/8 0:52
 *  Description :
 */
interface ArticleService {

    fun getArticleById(id: Long?): Article?

    fun getArticleByIds(ids: List<Long>?): List<Article>?

    fun getArticleByTitle(title: String?, pageable: Pageable): Page<Article>?

    fun getArticlesByCategoryId(categoryId: Long?, pageable: Pageable): Page<Article>?

    fun getArticles(pageable: Pageable): Page<Article>

    fun getHotArticles(): List<Article>?

    fun addArticle(articleInput: ArticleInput?): Article?

    fun updateArticleById(id: Long?, articleInput: ArticleInput?): Article?

    fun updateArticleLike(id: Long?): Boolean

    fun deleteArticleById(id: Long?): Boolean

    fun deleteArticleByIds(ids: List<Long>?): Int

}