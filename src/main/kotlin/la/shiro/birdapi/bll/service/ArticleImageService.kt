package la.shiro.birdapi.bll.service

import la.shiro.birdapi.model.entity.ArticleImage
import la.shiro.birdapi.model.input.ArticleImageInput
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.multipart.MultipartFile

/**
 *  author: Rin Shiro
 *  Date: 23/4/11 1:16
 *  Description :
 */
interface ArticleImageService {

    fun getArticleImages(pageable: Pageable): Page<ArticleImage>?

    fun getArticleImageById(id: Long): ArticleImage?

    fun addArticleImage(imageTitle: String?, articleId: Long?, file: MultipartFile): ArticleImage?

    fun updateArticleImage(articleImageInput: ArticleImageInput?): ArticleImage?

    fun deleteArticleImage(id: Long): Boolean

    fun deleteArticleImageByArticleId(articleId: Long): Boolean

    fun deleteArticleImageByIds(ids: List<Long>): Int

}