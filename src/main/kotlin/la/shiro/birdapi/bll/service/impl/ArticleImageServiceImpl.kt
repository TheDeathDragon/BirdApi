package la.shiro.birdapi.bll.service.impl

import la.shiro.birdapi.bll.service.ArticleImageService
import la.shiro.birdapi.dal.ArticleImageRepository
import la.shiro.birdapi.dal.ArticleRepository
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
        val fileUtils = FileUtils()
        val path = fileUtils.uploadFile(file, DEFAULT_ARTICLE_IMG_UPLOAD_PATH)
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
            title = title,
            url = url,
            path = path,
            articleId = articleId,
        )
        return articleImageRepository.insert(articleInput)
    }

    override fun updateArticleImage(articleImageInput: ArticleImageInput?): ArticleImage? {
        return articleImageInput?.let {
            it.id?.let {
                articleImageRepository.update(articleImageInput)
            }
        }
    }

    override fun deleteArticleImage(id: Long): Boolean {
        return if (articleImageRepository.existsById(id)) {
            try {
                val articleImage = articleImageRepository.findById(id).orElse(null)
                articleImage?.path?.let {
                    FileUtils().deleteFile(it)
                }
            } catch (e: Exception) {
                println("deleteArticleImage --> Failed to delete file --> id = $id")
            }
            articleImageRepository.deleteById(id)
            return true
        } else false
    }

    override fun deleteArticleImageByArticleId(articleId: Long): Int {
        var count = 0
        val articleImages: List<ArticleImage> = articleImageRepository.findAllByArticleId(articleId)
        articleImages.forEach {
            try {
                articleImageRepository.deleteById(it.id)
                it.path.let { it1 -> FileUtils().deleteFile(it1) }
                count++
            } catch (e: Exception) {
                println("deleteArticleImageByArticleId --> Failed to delete file : ${it.path}")
            }
        }
        return count
    }

    override fun deleteArticleImageByIds(ids: List<Long>): Int {
        var count = 0
        ids.forEach {
            try {
                val articleImage = articleImageRepository.findById(it).orElse(null)
                articleImage?.path?.let {
                    FileUtils().deleteFile(articleImage.path)
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