package la.shiro.birdapi.dal

import la.shiro.birdapi.model.entity.BirdImage
import la.shiro.birdapi.util.FileUtils
import org.babyfish.jimmer.spring.repository.KRepository
import org.springframework.stereotype.Repository

/**
 * <p>
 * BirdImageRepository 接口
 * </p>
 *
 * @author Rin Shiro
 * @date 2023-04-07
 */
@Repository
interface BirdImageRepository : KRepository<BirdImage, Long> {
    fun findAllByBirdId(birdId: Long): List<BirdImage>

    fun deleteBirdImageByBirdId(birdId: Long): Int {
        var count = 0
        val birdImages = findAllByBirdId(birdId)
        birdImages.forEach {
            try {
                FileUtils.deleteFile(it.path)
            } catch (e: Exception) {
                println("deleteArticleImage --> Failed to delete file --> path = ${it.path}")
            }
            deleteById(it.id)
            count++
        }
        return count
    }
}

