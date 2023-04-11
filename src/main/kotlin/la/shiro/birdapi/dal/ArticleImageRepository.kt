package la.shiro.birdapi.dal

import la.shiro.birdapi.model.entity.ArticleImage
import la.shiro.birdapi.util.FileUtils
import org.babyfish.jimmer.spring.repository.KRepository
import org.springframework.stereotype.Repository

/**
 * <p>
 * ArticleImageRepository 接口
 * </p>
 *
 * @author Rin Shiro
 * @date 2023-04-07
 */
@Repository
interface ArticleImageRepository : KRepository<ArticleImage, Long> {
    fun findAllByArticleId(articleId: Long): List<ArticleImage>

    fun deleteArticleImageByArticleId(articleId: Long): Int{
        var count = 0
        val articleImages: List<ArticleImage> = findAllByArticleId(articleId)
        articleImages.forEach {
            try {
                deleteById(it.id)
                it.path.let { it1 -> FileUtils.deleteFile(it1) }
                count++
            } catch (e: Exception) {
                println("deleteArticleImageByArticleId --> Failed to delete file : ${it.path}")
            }
        }
        return count
    }
}

