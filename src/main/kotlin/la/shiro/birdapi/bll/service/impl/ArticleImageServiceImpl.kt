package la.shiro.birdapi.bll.service.impl

import la.shiro.birdapi.bll.service.ArticleImageService
import la.shiro.birdapi.dal.ArticleImageRepository
import la.shiro.birdapi.model.common.DEFAULT_ARTICLE_IMG_UPLOAD_PATH
import la.shiro.birdapi.model.entity.ArticleImage
import la.shiro.birdapi.model.input.ArticleImageInput
import la.shiro.birdapi.util.FileUtils
import la.shiro.birdapi.util.ImageUtil
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

/**
 *  author: Rin Shiro
 *  Date: 23/4/11 1:17
 *  Description :
 */
@Transactional
@Service
class ArticleImageServiceImpl(
    private val articleImageRepository: ArticleImageRepository
) : ArticleImageService {
    override fun getArticleImages(pageable: Pageable): Page<ArticleImage> {
        return articleImageRepository.findAll(pageable)
    }

    override fun getArticleImageById(id: Long): ArticleImage? {
        return articleImageRepository.findById(id).orElse(null)
    }

    override fun addArticleImage(imageTitle: String?, articleId: Long?, file: MultipartFile): ArticleImage? {
        val path = FileUtils.uploadFile(file, DEFAULT_ARTICLE_IMG_UPLOAD_PATH)
        val url = ImageUtil.getImgUrl(path)
        var title = imageTitle
        if (imageTitle == null) {
            file.originalFilename?.let {
                title = it.substring(
                    0, it.lastIndexOf(".")
                )
            }
            title = (System.currentTimeMillis().toString() + "_" + title).replace(" ", "_")
        }
        val articleInput = ArticleImageInput(
            id = null,
            articleId = articleId,
            title = title,
            url = url,
            path = path
        )
        return articleImageRepository.insert(articleInput)
    }

    override fun updateArticleImage(articleImageInput: ArticleImageInput, file: MultipartFile): ArticleImage? {
        val articleImage = articleImageRepository.findById(articleImageInput.id!!).orElse(null) ?: return null
        FileUtils.deleteFile(articleImage.path)
        val path = FileUtils.uploadFile(file, DEFAULT_ARTICLE_IMG_UPLOAD_PATH)
        val url = ImageUtil.getImgUrl(path)
        var title = articleImageInput.title
        if (articleImageInput.title == null) {
            file.originalFilename?.let {
                title = it.substring(
                    0, it.lastIndexOf(".")
                )
            }
            title = (System.currentTimeMillis().toString() + "_" + title).replace(" ", "_")
        }
        val newArticleImageInput = ArticleImageInput(
            id = articleImageInput.id,
            articleId = articleImageInput.articleId,
            title = title,
            url = url,
            path = path
        )
        return articleImageRepository.update(newArticleImageInput)
    }

    override fun deleteArticleImage(id: Long): Boolean {
        return if (articleImageRepository.existsById(id)) {
            try {
                val articleImage = articleImageRepository.findById(id).orElse(null)
                articleImage?.path?.let {
                    FileUtils.deleteFile(it)
                }
            } catch (e: Exception) {
                println("deleteArticleImage --> Failed to delete file --> id = $id")
            }
            articleImageRepository.deleteById(id)
            return true
        } else false
    }

    override fun deleteArticleImageByArticleId(articleId: Long): Int {
        return articleImageRepository.deleteArticleImageByArticleId(articleId)
    }

    override fun deleteArticleImageByIds(ids: List<Long>): Int {
        var count = 0
        ids.forEach {
            try {
                val articleImage = articleImageRepository.findById(it).orElse(null)
                articleImage?.path?.let {
                    FileUtils.deleteFile(articleImage.path)
                }
                articleImageRepository.deleteById(it)
                count++
            } catch (e: Exception) {
                println("deleteArticleImageByIds --> Failed to delete file --> id = $it")
            }
        }
        return count
    }

}