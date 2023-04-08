package la.shiro.birdapi.bll.service

import la.shiro.birdapi.model.entity.ArticleCollection
import la.shiro.birdapi.model.input.ArticleCollectionInput
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

/**
 *  author: Rin Shiro
 *  Date: 23/4/8 16:26
 *  Description :
 */
interface ArticleCollectionService {
    fun getArticleCollections(pageable: Pageable): Page<ArticleCollection>?
    fun getArticleCollectionById(id: Long?): ArticleCollection?
    fun getArticleCollectionByIds(ids: List<Long>?): List<ArticleCollection>?
    fun getArticleCollectionByUserId(userId: Long?): List<ArticleCollection>?
    fun getArticleCollectionByArticleId(articleId: Long?): List<ArticleCollection>?
    fun addArticleCollection(articleCollectionInput: ArticleCollectionInput?): ArticleCollection?
    fun updateArticleCollectionById(id: Long?, articleCollectionInput: ArticleCollectionInput?): ArticleCollection?
    fun deleteArticleCollectionById(id: Long?): Boolean
    fun deleteArticleCollectionByIds(ids: List<Long>?): Int
}