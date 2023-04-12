package la.shiro.birdapi.bll.service.impl

import la.shiro.birdapi.bll.service.BirdImageService
import la.shiro.birdapi.dal.BirdImageRepository
import la.shiro.birdapi.model.common.DEFAULT_BIRD_IMG_UPLOAD_PATH
import la.shiro.birdapi.model.entity.BirdImage
import la.shiro.birdapi.model.input.BirdImageInput
import la.shiro.birdapi.util.FileUtils
import la.shiro.birdapi.util.ImageUtil
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

/**
 *  author: Rin Shiro
 *  Date: 23/4/11 10:36
 *  Description :
 */
@Transactional
@Service
class BirdImageServiceImpl(
    private val birdImageRepository: BirdImageRepository
) : BirdImageService {
    override fun getBirdImages(pageable: Pageable): Page<BirdImage>? {
        return birdImageRepository.findAll(pageable)
    }

    override fun getBirdImageById(id: Long): BirdImage? {
        return birdImageRepository.findById(id).orElse(null)
    }

    override fun addBirdImage(imageTitle: String?, birdId: Long?, file: MultipartFile): BirdImage? {
        val path = FileUtils.uploadFile(file, DEFAULT_BIRD_IMG_UPLOAD_PATH)
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
        val birdImageInput = BirdImageInput(
            id = null,
            birdId = birdId,
            title = title,
            url = url,
            path = path
        )
        return birdImageRepository.insert(birdImageInput)
    }

    override fun updateBirdImage(birdImageInput: BirdImageInput, file: MultipartFile): BirdImage? {
        val birdImage = birdImageRepository.findById(birdImageInput.id!!).orElse(null) ?: return null
        FileUtils.deleteFile(birdImage.path)
        val path = FileUtils.uploadFile(file, DEFAULT_BIRD_IMG_UPLOAD_PATH)
        val url = ImageUtil.getImgUrl(path)
        var title = birdImageInput.title
        if (birdImageInput.title == null) {
            file.originalFilename?.let {
                title = it.substring(
                    0, it.lastIndexOf(".")
                )
            }
            title = (System.currentTimeMillis().toString() + "_" + title).replace(" ", "_")
        }
        val newBirdImageInput = BirdImageInput(
            id = birdImageInput.id,
            birdId = birdImageInput.birdId,
            title = title,
            url = url,
            path = path
        )
        return birdImageRepository.update(newBirdImageInput)
    }

    override fun deleteBirdImage(id: Long): Boolean {
        return if (birdImageRepository.existsById(id)) {
            try {
                val birdImage = birdImageRepository.findById(id).orElse(null)
                birdImage?.let {
                    FileUtils.deleteFile(it.path)
                }
            } catch (e: Exception) {
                println("deleteArticleImage --> Failed to delete file --> id = $id")
            }
            birdImageRepository.deleteById(id)
            return true
        } else {
            false
        }
    }

    override fun deleteBirdImageByBirdId(birdId: Long): Int {
        return birdImageRepository.deleteBirdImageByBirdId(birdId)
    }

    override fun deleteBirdImageByIds(ids: List<Long>): Int {
        var count = 0
        ids.forEach {
            try {
                val birdImage = birdImageRepository.findById(it).orElse(null)
                birdImage?.let {
                    FileUtils.deleteFile(birdImage.path)
                }
            } catch (e: Exception) {
                println("deleteArticleImage --> Failed to delete file --> id = $it")
            }
            birdImageRepository.deleteById(it)
            count++
        }
        return count
    }
}
